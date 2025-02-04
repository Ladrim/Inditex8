package org.alfonso.infraestructure.repositories.h2;

import org.alfonso.application.ProductRepository;
import org.alfonso.domain.product.Product;
import org.alfonso.domain.product.ProductId;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductInH2Repository implements ProductRepository
{
    private final ProductInH2Adapter adapter;
    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate jdbc;

    public ProductInH2Repository(ProductInH2Adapter adapter, DataSource dataSource)
    {
        this.adapter = adapter;
        this.dataSource = dataSource;
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    // FIND:
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public Optional<Product> find(ProductId productId)
    {
        MapSqlParameterSource productParams = new MapSqlParameterSource();
        productParams.addValue("id", productId.getValue().toString());

        Optional<ProductInH2Entity> optionalProductInH2Entity = jdbc.query("SELECT * FROM PRODUCT WHERE PRODUCT_ID = :id", productParams, this::toProductInH2Entity).stream().findFirst();
        List<PriceInH2Entity> priceInH2Entities = jdbc.query("SELECT * FROM PRICE WHERE PRODUCT_ID = :id", productParams, this::toPriceInH2Entity);

        return optionalProductInH2Entity.map(it-> adapter.toDomain(it, priceInH2Entities));
    }

    // DELETE:
    //------------------------------------------------------------------------------------------------------------------

    @Override @Transactional
    public void delete(ProductId productId)
    {
        MapSqlParameterSource productParams = new MapSqlParameterSource();
        productParams.addValue("id", productId.getValue().toString());

        jdbc.update("DELETE FROM PRICE WHERE PRODUCT_ID = :id", productParams);
        jdbc.update("DELETE FROM PRODUCT WHERE PRODUCT_ID = :id", productParams);
    }

    // SAVE:
    //------------------------------------------------------------------------------------------------------------------

    @Override @Transactional
    public void save(Product product)
    {
        var savedProduct = find(product.getId());

        if (savedProduct.isEmpty())
        {
            insertProduct(product);
        }
        else
        {
            delete(product.getId());
            insertProduct(product);
        }
    }

    private void insertProduct(Product product)
    {
        ProductInH2Entity productInH2Entity = adapter.toEntity(product);
        List<PriceInH2Entity> priceInH2Entities = product.getPrices().stream().map(it-> adapter.toEntity(it,product)).toList();

        insertProduct(productInH2Entity);
        priceInH2Entities.forEach(this::insertPrice);
    }

    private void insertProduct(ProductInH2Entity productInH2Entity) {

        MapSqlParameterSource productParams = new MapSqlParameterSource();
        productParams.addValue("id", productInH2Entity.getProductId());
        productParams.addValue("name", productInH2Entity.getProductName());

        jdbc.update("INSERT INTO PRODUCT(PRODUCT_ID, PRODUCT_NAME) VALUES(:id, :name)", productParams);
    }

    private void insertPrice(PriceInH2Entity priceInH2Entity)
    {
        MapSqlParameterSource priceParams = new MapSqlParameterSource();
        priceParams.addValue("priceId", priceInH2Entity.getPriceId());
        priceParams.addValue("brandId", priceInH2Entity.getBrandId());
        priceParams.addValue("startDate", priceInH2Entity.getStartDate());
        priceParams.addValue("endDate", priceInH2Entity.getEndDate());
        priceParams.addValue("currencyType", priceInH2Entity.getCurrencyType());
        priceParams.addValue("amount", priceInH2Entity.getAmount());
        priceParams.addValue("priority", priceInH2Entity.getPriority());
        priceParams.addValue("productId", priceInH2Entity.getProductId());

        jdbc.update("INSERT INTO PRICE(PRICE_ID, BRAND_ID, START_DATE, END_DATE, CURRENCY_TYPE, AMOUNT," +
            "PRIORITY, PRODUCT_ID) VALUES(:priceId, :brandId, :startDate, :endDate, :currencyType, :amount," +
            ":priority, :productId)", priceParams);
    }

    // ROWMAPERS:
    //------------------------------------------------------------------------------------------------------------------

    private ProductInH2Entity toProductInH2Entity(ResultSet resultSet, int rownum) throws SQLException
    {
        return new ProductInH2Entity
        (
            resultSet.getString("PRODUCT_ID"),
            resultSet.getString("PRODUCT_NAME")
        );
    }

    private PriceInH2Entity toPriceInH2Entity(ResultSet resultSet, int rownum) throws SQLException
    {
        return new PriceInH2Entity
        (
            resultSet.getString("PRICE_ID"),
            resultSet.getString("BRAND_ID"),
            resultSet.getString("START_DATE"),
            resultSet.getString("END_DATE"),
            resultSet.getString("CURRENCY_TYPE"),
            resultSet.getFloat("AMOUNT"),
            resultSet.getInt("PRIORITY"),
            resultSet.getString("PRODUCT_ID")
        );
    }


    // SHIT:
    //------------------------------------------------------------------------------------------------------------------

    private void saveProduct1(Product product)
    {
        List<PriceInH2Entity> priceInH2Entities = product.getPrices().stream()
                .map(it-> adapter.toEntity(it,product))
                .toList();

        priceInH2Entities.forEach(this::insertPrice2);
    }

    public void saveProduct2(Product product) {

        MapSqlParameterSource productParams = new MapSqlParameterSource();
        productParams.addValue("id", product.getId().getValue().toString());
        Optional<ProductInH2Entity> savedProduct = jdbc.query("SELECT * FROM PRODUCT WHERE PRODUCT_ID = :id", productParams, this::toProductInH2Entity).stream().findFirst();
        List<PriceInH2Entity> savedPrices = jdbc.query("SELECT * FROM PRICE WHERE PRODUCT_ID = :id", productParams, this::toPriceInH2Entity);

        if (savedProduct.isEmpty()) {

            insertProduct(product);

        }
        else
        {
            var productEntity = adapter.toEntity(product);
            var priceEntities = adapter.toPriceEntities(product);

            if (!productEntity.equals(savedProduct.get())) {

                // updateProduct
            }

            findModified(priceEntities, savedPrices).forEach( it -> );
            findAdded(priceEntities, savedPrices).forEach( it -> insertPrice2(it));
            findRemoved(priceEntities, savedPrices).forEach( it -> );
        }
    }

    private List<PriceInH2Entity>findRemoved(List<PriceInH2Entity>newPrices, List<PriceInH2Entity> oldPrices) {

    }

    private List<PriceInH2Entity>findAdded(List<PriceInH2Entity>newPrices, List<PriceInH2Entity> oldPrices) {

    }

    private List<PriceInH2Entity>findModified(List<PriceInH2Entity>newPrices, List<PriceInH2Entity> oldPrices) {

    }

    private void insertPrice2(PriceInH2Entity priceInH2Entity)
    {
        MapSqlParameterSource priceParams = new MapSqlParameterSource();
        priceParams.addValue("priceId", priceInH2Entity.getPriceId());
        priceParams.addValue("brandId", priceInH2Entity.getBrandId());
        priceParams.addValue("startDate", priceInH2Entity.getStartDate());
        priceParams.addValue("endDate", priceInH2Entity.getEndDate());
        priceParams.addValue("currencyType", priceInH2Entity.getCurrencyType());
        priceParams.addValue("amount", priceInH2Entity.getAmount());
        priceParams.addValue("priority", priceInH2Entity.getPriority());
        priceParams.addValue("productId", priceInH2Entity.getProductId());

        Optional<PriceInH2Entity> optionalPrice = jdbc.query("SELECT * FROM PRICE WHERE PRICE_ID = :priceId", priceParams, this::toPriceInH2Entity).stream().findFirst();

        if(optionalPrice.isEmpty())
        {
            jdbc.update("INSERT INTO PRICE(PRICE_ID, BRAND_ID, START_DATE, END_DATE, CURRENCY_TYPE, AMOUNT," +
                    "PRIORITY, PRODUCT_ID) VALUES(:priceId, :brandId, :startDate, :endDate, :currencyType, :amount," +
                    ":priority, :productId)", priceParams);
        }
    }
}
