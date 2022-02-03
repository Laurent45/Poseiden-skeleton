package serviceTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.impl.RuleNameService;
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
@SpringBootTest(classes = RuleNameService.class)
public class RuleNameServiceUT {

    @Autowired
    private RuleNameService ruleNameServiceUT;

    @MockBean
    private RuleNameRepository ruleNameRepository;

    @Test
    public void whenSaveBidListThenReturnRuleNameList() {
        RuleName ruleName = new RuleName("name1"
                , "description1"
                , "json1"
                , "template1"
                , "sqlStr1"
                , "sqlPart1");
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);

        Assert.assertEquals(ruleName, ruleNameServiceUT.saveModel(ruleName));
    }

    @Test
    public void whenGetModelByIdThenReturnRuleNameList() throws IdRequestUnknown {
        RuleName ruleName = new RuleName("name1"
                , "description1"
                , "json1"
                , "template1"
                , "sqlStr1"
                , "sqlPart1");
        ruleName.setId(1);
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));

        Assert.assertEquals(ruleName, ruleNameServiceUT.getModel(1));
    }

    @Test
    public void whenGetAllModelsThenReturnAllRuleName() {
        List<RuleName> ruleNameList = Arrays.asList(new RuleName("name1"
                , "description1"
                , "json1"
                , "template1"
                , "sqlStr1"
                , "sqlPart1"));
        when(ruleNameRepository.findAll()).thenReturn(ruleNameList);

        Assert.assertEquals(ruleNameList, ruleNameServiceUT.getAllModels());
    }
}
