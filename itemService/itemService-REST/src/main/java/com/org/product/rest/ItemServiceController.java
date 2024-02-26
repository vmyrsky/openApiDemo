package com.org.product.rest;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.org.product.entity.ItemDO;
import com.org.product.generated.model.Item;
import com.org.product.generated.model.SummaryItem;
import com.org.product.generated.rest.ItemApi;
import com.org.product.generated.rest.ItemsApi;
import com.org.product.services.GenericService;

/**
 * This class implements the item APIs generated from the OpenAPI document.
 */
@RestController
public class ItemServiceController implements ItemApi, ItemsApi {

    @Inject
    private GenericService<ItemDO> itemService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ItemApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Item> itemIdGet(Long id) {
        var item = this.itemService.getEntity(id);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SummaryItem>> itemsGet() {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }
}
