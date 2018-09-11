package cn.chipsgateway.preFilter;

import cn.chipsgateway.filter.AbstractChipsGatewayFilter;
import cn.chipsgateway.strategy.ChoiceCurrentLimitingFilterStrategy;
import cn.chipsgateway.strategy.ChoiceStrategy;
import cn.chipsgateway.support.GatewayHttpServletRequest;
import cn.chipsgateway.support.GatewayHttpServletResponse;

import static cn.chipsgateway.support.GatewayConstant.PREFILTER;

public class DefaultCurrentlimitingFilter extends AbstractChipsGatewayFilter {
    private ChoiceStrategy choiceCurrentLimitingFilterStrategy;

    public DefaultCurrentlimitingFilter(ChoiceCurrentLimitingFilterStrategy choiceCurrentLimitingFilterStrategy) {
        this.choiceCurrentLimitingFilterStrategy = choiceCurrentLimitingFilterStrategy;
    }

    public boolean shouldFilter() {
        return true;
    }

    public int filterOrder() {
        return -1000;
    }

    public String filterType() {
        return PREFILTER;
    }


    public boolean run(GatewayHttpServletRequest request, GatewayHttpServletResponse response) {
        return (boolean) choiceCurrentLimitingFilterStrategy.choice();
    }
}
