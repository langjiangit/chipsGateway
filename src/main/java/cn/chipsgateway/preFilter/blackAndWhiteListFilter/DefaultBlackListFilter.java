package cn.chipsgateway.preFilter.blackAndWhiteListFilter;

import cn.chipsgateway.context.RequestContext;
import cn.chipsgateway.filter.AbstractChipsGatewayFilter;
import cn.chipsgateway.support.GatewayHttpServletRequest;
import cn.chipsgateway.support.GatewayHttpServletResponse;
import cn.chipsgateway.utils.WebHelpUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static cn.chipsgateway.support.GatewayConstant.PREFILTER;

public class DefaultBlackListFilter extends AbstractListFilter {
    public boolean shouldFilter() {
        return false;
    }

    public int filterOrder() {
        return -999;
    }

    public String filterType() {
        return PREFILTER;
    }

    public boolean run(GatewayHttpServletRequest request, GatewayHttpServletResponse response) {
        RequestContext currentRequest = RequestContext.getCurrentRequest();
        String ipAddress = currentRequest.getRequestIP();
        String requestUrl = currentRequest.getRequestUrl();
        boolean b = isExistList(WebHelpUtils.getIpAddress(request), WebHelpUtils.getRequestUrl(request));
        if (b) {
            currentRequest.setFailedFilterIpVisitUrlErrorMessage();
            currentRequest.setFailedFilterType("BlackListFilter");
        }
        return !b;
    }

    public void addBlackList(String ip, List<String> urls) {
        addList(ip, urls);
    }

}
