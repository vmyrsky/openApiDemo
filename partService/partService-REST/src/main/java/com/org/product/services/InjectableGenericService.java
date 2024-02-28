package com.org.product.services;

import org.springframework.stereotype.Service;

import com.org.product.entity.PartDO;
import com.org.product.generic.model.Identifiable;
import com.org.product.generic.service.GenericService;

/**
 * This class is required only to introduce the generic service automatically for spring context.
 */
@Service
public class InjectableGenericService<T extends Identifiable> extends GenericService<T> {

}
