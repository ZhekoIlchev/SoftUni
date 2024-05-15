package com.example.Exercise_09_XML_Processing.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductWithoutBuyerViewRootDto {
    @XmlElement(name = "product")
    List<ProductWithoutBuyerViewDto> products;

    public List<ProductWithoutBuyerViewDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWithoutBuyerViewDto> products) {
        this.products = products;
    }
}