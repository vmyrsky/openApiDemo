package com.org.product.rest;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.org.product.entity.PartDO;
import com.org.product.generated.model.Part;
import com.org.product.generated.model.SummaryPart;
import com.org.product.generated.rest.PartApi;
import com.org.product.generated.rest.PartsApi;
import com.org.product.generic.service.GenericService;

/**
 * This class implements the item APIs generated from the OpenAPI document.
 */
@RestController
public class PartServiceController implements PartApi, PartsApi {

    @Inject
    private GenericService<PartDO> partService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return PartApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Part> partIdGet(Long id) {
        var item = this.partService.getEntity(id);
        return new ResponseEntity<Part>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SummaryPart>> partsGet() {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }
}
