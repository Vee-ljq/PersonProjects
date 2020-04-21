package cn.sofwin.app.service.imp;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageService {
    // 阿里云的accesKey
// AccessKey ID
    private static final String ALI_ACCESS_KEY_ID = "LTAI4FrZ35C4u5q5sTBfvZw6";
    // Access Key Secret
    private static final String ALI_ACCESS_SECRET = "6IbOlluDoxWzcHQHPgxkcdXQ5JPDPL";
    // 阿里云短信签名
    private static final String ALI_MESSAGE_SIGN_NAME = "百蔬鲜一站式";

    public static boolean sendSms(String mobile, String code, String tmplateCode) {
        //产品名称:云通信短信API产品,开发者无需替换
        String productId = "Dysmsapi";
        //产品域名,开发者无需替换
        String domain = "dysmsapi.aliyuncs.com";

        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ALI_ACCESS_KEY_ID,
                ALI_ACCESS_SECRET);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", productId, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(mobile);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(ALI_MESSAGE_SIGN_NAME); // TODO 修改成自己的
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(tmplateCode); // TODO 修改成自己的
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        // request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        request.setTemplateParam(code);
        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {

            System.out.println("发送成功");
            return true;
        } else {
            System.out.println("发送失败");
            return false;
        }
    }

    //这里是短信模板
    public static String muban="SMS_182679084";
}
