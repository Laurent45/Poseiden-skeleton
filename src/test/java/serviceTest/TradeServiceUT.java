package serviceTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.impl.TradeService;
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
@SpringBootTest(classes = TradeService.class)
public class TradeServiceUT {

    @Autowired
    private TradeService tradeServiceUT;

    @MockBean
    private TradeRepository tradeRepository;

    @Test
    public void whenSaveBidListThenReturnTradeList() {
        Trade trade = new Trade(1
                , "account1"
                , "type1"
                , 2D);
        when(tradeRepository.save(trade)).thenReturn(trade);

        Assert.assertEquals(trade, tradeServiceUT.saveModel(trade));
    }

    @Test
    public void whenGetModelByIdThenReturnTradeList() throws IdRequestUnknown {
        Trade trade = new Trade(1
                , "account1"
                , "type1"
                , 2D);
        trade.setId(1);
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        Assert.assertEquals(trade, tradeServiceUT.getModel(1));
    }

    @Test
    public void whenGetAllModelsThenReturnAllRuleName() {
        List<Trade> tradeList = Arrays.asList(new Trade(1
                , "account1"
                , "type1"
                , 2D));
        when(tradeRepository.findAll()).thenReturn(tradeList);

        Assert.assertEquals(tradeList, tradeServiceUT.getAllModels());
    }
}
