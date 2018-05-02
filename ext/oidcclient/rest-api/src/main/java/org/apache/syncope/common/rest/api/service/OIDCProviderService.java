/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.common.rest.api.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ResponseHeader;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.syncope.common.lib.to.OIDCProviderTO;
import java.util.List;
import java.util.Set;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
import org.apache.syncope.common.lib.SyncopeConstants;
import org.apache.syncope.common.rest.api.RESTHeaders;

/**
 * REST operations for OpenID Connect Providers.
 */
@Api(tags = "OIDCProviders", authorizations = {
    @Authorization(value = "BasicAuthentication"),
    @Authorization(value = "Bearer") })
@Path("oidcclient/providers")
public interface OIDCProviderService extends JAXRSService {

    /**
     * Returns the list of available OIDCProviderActions implementations.
     *
     * @return the list of available OIDCProviderActions implementations
     */
    @GET
    @Path("actionsClasses")
    @Produces({ MediaType.APPLICATION_JSON })
    Set<String> getActionsClasses();

    /**
     * Returns a list of all defined OIDC Providers.
     *
     * @return list of all defined OIDC Providers
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, SyncopeConstants.APPLICATION_YAML, MediaType.APPLICATION_XML })
    List<OIDCProviderTO> list();

    /**
     * Returns the OIDC Provider with matching key, if available.
     *
     * @param key OIDC Provider's key
     * @return OIDC Providers with matching key, if available
     */
    @GET
    @Path("{key}")
    @Produces({ MediaType.APPLICATION_JSON, SyncopeConstants.APPLICATION_YAML, MediaType.APPLICATION_XML })
    OIDCProviderTO read(@PathParam("key") String key);

    /**
     * Creates a new OIDC Provider.
     *
     * @param oidcProviderTO OpenID Connect Provider configuration to be stored
     * @return Response object featuring Location header of created OIDC Provider
     */
    @ApiResponses(
            @ApiResponse(code = 201,
                    message = "SecurityQuestion successfully created", responseHeaders = {
                @ResponseHeader(name = RESTHeaders.RESOURCE_KEY, response = String.class,
                        description = "UUID generated for the entity created")
                ,
                @ResponseHeader(name = HttpHeaders.LOCATION, response = String.class,
                        description = "URL of the entity created") }))
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, SyncopeConstants.APPLICATION_YAML, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, SyncopeConstants.APPLICATION_YAML, MediaType.APPLICATION_XML })
    Response create(OIDCProviderTO oidcProviderTO);

    /**
     * Creates a new OIDC Provider by using its Discovery Document.
     *
     * @param oidcProviderTO OpenID Connect Provider configuration to be stored  
     * @return Response object featuring Location header of created OIDC Provider
     */
    @ApiResponses(
            @ApiResponse(code = 201,
                    message = "SecurityQuestion successfully created", responseHeaders = {
                @ResponseHeader(name = RESTHeaders.RESOURCE_KEY, response = String.class,
                        description = "UUID generated for the entity created")
                ,
                @ResponseHeader(name = HttpHeaders.LOCATION, response = String.class,
                        description = "URL of the entity created") }))
    @POST
    @Path("fromDiscovery")
    @Consumes({ MediaType.APPLICATION_JSON, SyncopeConstants.APPLICATION_YAML, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, SyncopeConstants.APPLICATION_YAML, MediaType.APPLICATION_XML })
    Response createFromDiscovery(OIDCProviderTO oidcProviderTO);

    /**
     * Updates the OIDC Provider with matching key.
     *
     * @param oidcProviderTO OpenID Connect Provider configuration to be stored
     */
    @ApiImplicitParams({
        @ApiImplicitParam(name = "key", paramType = "path", dataType = "string", value = "OIDC Provider's key") })
    @ApiResponses(
            @ApiResponse(code = 204, message = "Operation was successful"))
    @PUT
    @Path("{key}")
    @Consumes({ MediaType.APPLICATION_JSON, SyncopeConstants.APPLICATION_YAML, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, SyncopeConstants.APPLICATION_YAML, MediaType.APPLICATION_XML })
    void update(@NotNull OIDCProviderTO oidcProviderTO);

    /**
     * Deletes the OIDC Provider with matching key.
     *
     * @param key OIDC Provider key
     */
    @ApiResponses(
            @ApiResponse(code = 204, message = "Operation was successful"))
    @DELETE
    @Path("{key}")
    @Produces({ MediaType.APPLICATION_JSON, SyncopeConstants.APPLICATION_YAML, MediaType.APPLICATION_XML })
    void delete(@PathParam("key") String key);

}