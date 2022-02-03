package serviceTest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.impl.BidListService;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BidListService.class)
public class BidListServiceUT {

    @Autowired
    private BidListService bidListServiceUT;

    @MockBean
    private BidListRepository bidListRepository;

    @Test
    public void whenSaveBidListThenReturnBidList() {
        BidList bidList = new BidList(1
                , "account1"
                , "type1"
                , 2D);
        when(bidListRepository.save(bidList)).thenReturn(bidList);

        Assert.assertEquals(bidList, bidListServiceUT.saveModel(bidList));
    }

    @Test
    public void whenGetModelByIdThenReturnBidList() throws IdRequestUnknown {
        BidList bidList = new BidList(1
                , "account1"
                , "type1"
                , 2D);
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));

        Assert.assertEquals(bidList, bidListServiceUT.getModel(1));
    }

    @Test
    public void whenGetAllModelsThenReturnAllModels() {
        List<BidList> listBidList = Arrays.asList(new BidList(1
                , "account1"
                , "type1"
                , 2D));
        when(bidListRepository.findAll()).thenReturn(listBidList);

        Assert.assertEquals(listBidList, bidListServiceUT.getAllModels());
    }
}
