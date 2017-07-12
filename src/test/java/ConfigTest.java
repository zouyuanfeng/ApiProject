import com.itzyf.service.WxService;
import org.junit.Test;

/**
 * @author 依风听雨
 * @version 创建时间：2017/7/12 10:01
 */
public class ConfigTest {

    @Test
    public void testConfig() {
        String aa = new WxService().authorize("http:\\baidu.com");
        System.out.print(aa);
    }
}
