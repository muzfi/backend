package com.example.muzfi.Util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Component
public class OktaRestClient {

    private final RestTemplate restTemplate;

    private final HttpHeaders headers;

    @Value("${api.okta.key}")
    private String apiKey;

    private final Logger logger = Logger.getLogger(OktaRestClient.class.getName());

    @Autowired
    public OktaRestClient(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
    }


    // Okta groups defined for user roles
    // Get okta group by its group name which is equal to user role
    public ResponseEntity<?> getOktaGroupByName(String groupName) {
        try {

            UriComponentsBuilder uri = UriComponentsBuilder.fromUriString("/api/v1/groups")
                    .queryParam("q", groupName);

            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.set("Authorization", "SSWS " + apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<?> responseEntity = restTemplate.exchange(uri.toUriString(), HttpMethod.GET, entity, String.class);

            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                return responseEntity;
            } else {
                return new ResponseEntity<>("No Okta Groups Available", HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Okta groups defined for user roles
    // Get okta groups for the selected userId
    public ResponseEntity<?> getOktaGroupsByUserId(String userOktaId) {
        try {
            final String uri = "/api/v1/users/" + userOktaId + "/groups";

            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.set("Authorization", "SSWS " + apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                return responseEntity;
            } else {
                return new ResponseEntity<>("No Okta Groups Available for the user", HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Okta groups defined for user roles
    // User add to a selected okta group which is related to user role
    public ResponseEntity<?> addUserToOktaGroup(String userOktaId, String groupName) {
        try {
            ResponseEntity<?> group = getOktaGroupByName(groupName);
            String groupId = getGroupId(Objects.requireNonNull(group.getBody()).toString(), groupName);

            final String uri = "/api/v1/groups/" + groupId + "/users/" + userOktaId;

            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.set("Authorization", "SSWS " + apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class);

            if (responseEntity.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
                return new ResponseEntity<>("User group update operation successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User group update operation failed", HttpStatus.NOT_MODIFIED);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> removeUserFromOktaGroup(String userOktaId, String groupName) {
        try {
            ResponseEntity<?> userGroups = getOktaGroupByName(groupName);
            String groupId = getGroupId(Objects.requireNonNull(userGroups.getBody()).toString(), groupName);

            final String uri = "/api/v1/groups/" + groupId + "/users/" + userOktaId;

            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.set("Authorization", "SSWS " + apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);

            if (responseEntity.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
                return new ResponseEntity<>("User group update operation successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User group update operation failed", HttpStatus.NOT_MODIFIED);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get groupId from group json response
    private String getGroupId(String jsonResponse, String groupName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String groupId = null;

            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            for (JsonNode item : jsonNode) {
                JsonNode profileNode = item.get("profile");
                if (profileNode != null) {
                    String name = profileNode.get("name").asText();
                    if (groupName.equals(name)) {
                        groupId = item.get("id").asText();
                        break;
                    }
                }
            }

            return groupId;
        } catch (Exception e) {
            logger.severe("An error occurred: " + e.getMessage());
            return null;
        }
    }
}
