package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author 410775541@qq.com <br>
 * @since 0.5.1
 */
public class BaiDuReShouProcessor implements PageProcessor {

    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setCharset("UTF-8");

    private static final int voteNum = 1000;


    @Override
    public void process(Page page) {
        List<String> name=page.getHtml().xpath("//ul[@class='s-hotsearch-content']/li/a/span[@class='title-content-title']/text()").all();
        List<String> link=page.getHtml().xpath("//ul[@class='s-hotsearch-content']/li/a/@href").all();

        System.out.println("name:"+name);
        System.out.println("link:"+link);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BaiDuReShouProcessor()).
                addUrl("http://www.baidu.com").
                addPipeline(new FilePipeline("C:\\webmagic\\")).
                thread(5).
                run();
    }
}
