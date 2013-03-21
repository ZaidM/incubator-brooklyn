/*
 * Copyright 2013 by Cloudsoft Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package brooklyn.entity.messaging.kafka;

import java.util.Map;

import brooklyn.config.ConfigKey;
import brooklyn.location.basic.SshMachineLocation;
import brooklyn.util.MutableMap;

public class KafkaBrokerSshDriver extends AbstractfKafkaSshDriver implements KafkaBrokerDriver {

    public KafkaBrokerSshDriver(KafkaBrokerImpl entity, SshMachineLocation machine) {
        super(entity, machine);
    }

    @Override
    protected Map<String, Integer> getPortMap() {
        return MutableMap.of("kafkaPort", getKafkaPort());
    }

    @Override
    protected ConfigKey<String> getConfigTemplateKey() {
        return KafkaBroker.SERVER_CONFIG_TEMPLATE;
    }

    @Override
    protected String getConfigFileName() {
        return "server.properties";
    }

    @Override
    protected String getLaunchScriptName() {
        return "kafka-server-start.sh";
    }

    @Override
    protected String getProcessIdentifier() {
        return "kafka\\.Kafka";
    }

    @Override
    public Integer getKafkaPort() {
        return getEntity().getAttribute(KafkaBroker.KAFKA_PORT);
    }

}
