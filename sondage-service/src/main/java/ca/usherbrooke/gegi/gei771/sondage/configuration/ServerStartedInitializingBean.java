package ca.usherbrooke.gegi.gei771.sondage.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 *
 * @author pthomas3
 */
@Component
@Slf4j
public class ServerStartedInitializingBean implements ApplicationRunner, ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    private int localPort;

    public int getLocalPort() {
        return localPort;
    }

    @Override
    public void run(ApplicationArguments aa) throws Exception {
        log.info("server started with args: {}", Arrays.toString(aa.getSourceArgs()));
    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent e) {
        localPort = e.getEmbeddedServletContainer().getPort();
        log.info("after runtime init, local server port: {}", localPort);
    }

}
