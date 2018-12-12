package winrm;

import io.cloudsoft.winrm4j.client.WinRmClientContext;
import io.cloudsoft.winrm4j.winrm.WinRmTool;
import io.cloudsoft.winrm4j.winrm.WinRmToolResponse;
import org.apache.http.client.config.AuthSchemes;

public class WinRM {
    public static void main(String[] args)  {
        WinRmClientContext context = WinRmClientContext.newInstance();
        WinRmTool.Builder builder = WinRmTool.Builder.builder("127.0.0.1:5985", "Administrator", "3910");
        builder.setAuthenticationScheme(AuthSchemes.NTLM);
        builder.port(5985);
        builder.useHttps(false);

        builder.context(context);
        WinRmTool tool =  builder.build();
        tool.setOperationTimeout(5000L);
        System.out.println("========");
        String command = "dir";
        WinRmToolResponse resp = tool.executeCommand(command);
        System.out.println(resp.getStatusCode());
        String out = resp.getStdOut();
        System.out.println(out);
        context.shutdown();

    }
}
