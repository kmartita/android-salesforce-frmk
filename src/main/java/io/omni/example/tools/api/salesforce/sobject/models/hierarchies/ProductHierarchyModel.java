package io.omni.example.tools.api.salesforce.sobject.models.hierarchies;

import io.omni.example.tools.api.salesforce.sobject.models.ProductModel;
import io.omni.example.tools.api.salesforce.recordtypes.ProductRecordType;

import java.util.List;
import java.util.stream.Collectors;

public class ProductHierarchyModel {

    private final ProductModel parent;
    private final List<ProductModel> children;

    public ProductHierarchyModel(final ProductModel parent, final List<ProductModel> children) {
        this.parent = parent;
        this.children = children;
    }

    //Parent
    public ProductModel getParent() {
        return parent;
    }

    public String getParentName() {
        return getParent().getName();
    }

    //Children
    public List<ProductModel> getChildren() {
        return children;
    }

    public List<String> getChildrenNames() {
        return getChildren()
                .stream()
                .map(ProductModel::getName)
                .collect(Collectors.toList());
    }

    public List<ProductModel> getDtpChildren() {
        return getChildren()
                .stream()
                .filter(ProductModel::isDtp)
                .collect(Collectors.toList());
    }

    public List<ProductModel> getSampleChildren() {
        return getChildren()
                .stream()
                .filter(product -> !product.isDtp())
                .collect(Collectors.toList());
    }

    public List<ProductModel> getChildrenByRecordType(ProductRecordType recordType) {
        return getChildren()
                .stream()
                .filter(product -> recordType.getRecordTypeId().equals(product.getRecordTypeId()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Product hierarchy model [Parent=%s] [Children=%s]",
                getParentName(), getChildrenNames());
    }
}
