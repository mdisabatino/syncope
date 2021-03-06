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

import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.syncope.common.lib.to.DynRealmTO;
import org.apache.syncope.common.rest.api.RESTHeaders;

/**
 * REST operations for dynamic realms.
 */
@Tag(name = "DynamicRealms")
@SecurityRequirements({
    @SecurityRequirement(name = "BasicAuthentication")
    , @SecurityRequirement(name = "Bearer") })
@Path("dynRealms")
public interface DynRealmService extends JAXRSService {

    /**
     * Returns a list of all dynamic realms.
     *
     * @return list of all dynamic realms.
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    List<DynRealmTO> list();

    /**
     * Returns dynamic realm with matching key.
     *
     * @param key dynamic realm key to be read
     * @return dynamic realm with matching key
     */
    @GET
    @Path("{key}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    DynRealmTO read(@NotNull @PathParam("key") String key);

    /**
     * Creates a new dynamic realm.
     *
     * @param dynDynRealmTO dynamic realm to be created
     * @return Response object featuring Location header of created dynamic realm
     */
    @ApiResponses(
            @ApiResponse(responseCode = "201",
                    description = "DynRealm successfully created", headers = {
                @Header(name = RESTHeaders.RESOURCE_KEY, schema =
                        @Schema(type = "string"),
                        description = "Key value for the entity created")
                , @Header(name = HttpHeaders.LOCATION, schema =
                        @Schema(type = "string"),
                        description = "URL of the entity created") }))
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Response create(@NotNull DynRealmTO dynDynRealmTO);

    /**
     * Updates the dynamic realm matching the provided key.
     *
     * @param dynDynRealmTO dynamic realm to be stored
     */
    @ApiResponses(
            @ApiResponse(responseCode = "204", description = "Operation was successful"))
    @PUT
    @Path("{key}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    void update(@NotNull DynRealmTO dynDynRealmTO);

    /**
     * Deletes the dynamic realm matching the provided key.
     *
     * @param key dynamic realm key to be deleted
     */
    @ApiResponses(
            @ApiResponse(responseCode = "204", description = "Operation was successful"))
    @DELETE
    @Path("{key}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    void delete(@NotNull @PathParam("key") String key);

}
