package com.domain.filter;

import com.domain.Offer.AbstractApply;
import com.domain.model.Product;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

public class AbstractApplyTest {

    @Mock
    private Product mockItem;

    @Test
    public void testApplyPrice() throws Exception {

        //Given, When
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        AbstractApply abstractApply = Mockito.mock(AbstractApply.class, Mockito.CALLS_REAL_METHODS);
        threadSafeItemList.add(mockItem);

        //Then
        assert abstractApply.filterPrice(threadSafeItemList,0) == 0.0d;
    }

}