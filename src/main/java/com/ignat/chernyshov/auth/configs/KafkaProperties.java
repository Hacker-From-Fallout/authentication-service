package com.ignat.chernyshov.auth.configs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaProperties {

    private List<String> bootstrapServers;
    private Map<String, String> topics = new HashMap<>();

    public Map<String, String> getTopics() {
        return topics;
    }

    public void setTopics(Map<String, String> topics) {
        this.topics = topics;
    }

    public String getCreateProfileTopic() {
        return topics.get("create-profile");
    }

    public String getUpdateUsernameTopic() {
        return topics.get("update-username");
    }

    public String getUpdateEmailTopic() {
        return topics.get("update-email");
    }

    public String getUpdatePhoneNumberTopic() {
        return topics.get("update-phone-number");
    }

    public String getDeleteProfileTopic() {
        return topics.get("delete-profile");
    }

    public List<String> getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(List<String> bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }
}
