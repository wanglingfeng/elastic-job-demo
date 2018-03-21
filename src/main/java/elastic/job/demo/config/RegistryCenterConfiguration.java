package elastic.job.demo.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册中心配置
 * 用于注册和协调作业分布式行为的组件，目前仅支持Zookeeper
 */
@Configuration
@ConditionalOnExpression("'${regCenter.serverList}'.length() > 0")
public class RegistryCenterConfiguration {

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter registryCenter(@Value("elastic.job.serverLists") String serverLists,
                                                  @Value("elastic.job.namespace") String namespace) {

        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverLists, namespace));
    }
}
