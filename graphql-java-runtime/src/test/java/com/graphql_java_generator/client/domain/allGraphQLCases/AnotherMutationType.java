package com.graphql_java_generator.client.domain.allGraphQLCases;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLQuery;
import com.graphql_java_generator.annotation.GraphQLScalar;
import com.graphql_java_generator.annotation.RequestType;
import com.graphql_java_generator.client.GraphQLConfiguration;
import com.graphql_java_generator.client.GraphqlClientUtils;
import com.graphql_java_generator.client.QueryExecutor;
import com.graphql_java_generator.client.QueryExecutorImpl;
import com.graphql_java_generator.client.request.Builder;
import com.graphql_java_generator.client.request.InputParameter;
import com.graphql_java_generator.client.request.ObjectResponse;
import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

import java.util.Date;

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@GraphQLQuery(name = "AnotherMutationType", type = RequestType.mutation)
public class AnotherMutationType {

	/** Logger for this class */
	private static Logger logger = LoggerFactory.getLogger(AnotherMutationType.class);

	final GraphqlClientUtils graphqlClientUtils = new GraphqlClientUtils();

	final GraphQLConfiguration configuration;

	/**
	 * This constructor expects the URI of the GraphQL server. This constructor works only for http servers, not for
	 * https ones.<BR/>
	 * For example: http://my.server.com/graphql
	 * 
	 * @param graphqlEndpoint
	 *            the http URI for the GraphQL endpoint
	 */
	public AnotherMutationType(String graphqlEndpoint) {
		this.configuration = new GraphQLConfiguration(graphqlEndpoint);
		CustomScalarRegistryInitializer.initCustomScalarRegistry();
		DirectiveRegistryInitializer.initDirectiveRegistry();
	}

	/**
	 * This constructor expects the URI of the GraphQL server. This constructor works only for https servers, not for
	 * http ones.<BR/>
	 * For example: https://my.server.com/graphql<BR/><BR/>
	 * {@link SSLContext} and {@link HostnameVerifier} are regular Java stuff. You'll find lots of documentation on the web. 
	 * The StarWars sample is based on the <A HREF="http://www.thinkcode.se/blog/2019/01/27/a-jersey-client-supporting-https">http://www.thinkcode.se/blog/2019/01/27/a-jersey-client-supporting-https</A> blog.
	 * But this sample implements a noHostVerification, which of course, is the simplest but the safest way to go.
	 * 
	 * @param graphqlEndpoint
	 *            the https URI for the GraphQL endpoint
	 * @param sslContext
	 * @param hostnameVerifier
	 */
	public AnotherMutationType(String graphqlEndpoint, SSLContext sslContext, HostnameVerifier hostnameVerifier) {
		this.configuration = new GraphQLConfiguration(graphqlEndpoint, sslContext, hostnameVerifier);
		CustomScalarRegistryInitializer.initCustomScalarRegistry();
		DirectiveRegistryInitializer.initDirectiveRegistry();
	}

	/**
	 * This constructor expects the URI of the GraphQL server and a configured JAX-RS client
	 * that gives the opportunity to customise the REST request<BR/>
	 * For example: http://my.server.com/graphql
	 *
	 * @param graphqlEndpoint
	 *            the http URI for the GraphQL endpoint
	 * @param client
	 *            {@link Client} javax.ws.rs.client.Client to support customization of the rest request
	 * @param objectMapper
	 *            {@link ObjectMapper} com.fasterxml.jackson.databind.ObjectMapper to support configurable mapping
	 */
	public AnotherMutationType(String graphqlEndpoint, Client client, ObjectMapper objectMapper) {
		this.configuration = new GraphQLConfiguration(graphqlEndpoint, client, objectMapper);
		CustomScalarRegistryInitializer.initCustomScalarRegistry();
		DirectiveRegistryInitializer.initDirectiveRegistry();
	}

	/**
	 * This method takes a full query definition, and executes the GraphQL request against the GraphQL server. That is,
	 * the query contains the full string that <B><U>follows</U></B> the query/mutation/subscription keyword.<BR/>
	 * For instance:
	 * 
	 * <PRE>
	 * Map<String, Object> params = new HashMap<>();
	 * params.put("heroParam", heroParamValue);
	 * params.put("skip", Boolean.FALSE);
	 * 
	 * Character c = myQyeryType.execWithBindValues(
	 * 		"{hero(param:?heroParam) @include(if:true) {id name @skip(if: ?skip) appearsIn friends {id name}}}",
	 * 		params);
	 * </PRE>
	 * 
	 * It offers a logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method is valid for queries/mutations/subscriptions which don't have bind variables, as there is no
	 * <I>parameters</I> argument to pass the list of values.
	 * 
	 * @param queryResponseDef
	 *            The response definition of the mutation, in the native GraphQL format (see here above). It must ommit the
	 *            query/mutation/subscription keyword, and start by the first { that follows.It may contain directives,
	 *            as explained in the GraphQL specs.
	 * @param parameters
	 *            The map of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}. The key is the parameter name, as
	 *            defined in the query (in the above sample: heroParam is an optional parameter and skip is a mandatory
	 *            one). The value is the parameter vale in its Java type (for instance a {@link Date} for the
	 *            {@link GraphQLScalarTypeDate}). The parameters which value is missing in this map will no be
	 *            transmitted toward the GraphQL server.
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	public AnotherMutationTypeResponse execWithBindValues(String queryResponseDef, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of mutation {} ", queryResponseDef);
		ObjectResponse objectResponse = getResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return exec(objectResponse, parameters);
	}

	/**
	 * This method takes a full query definition, and executes the GraphQL request against the GraphQL server. That is,
	 * the query contains the full string that <B><U>follows</U></B> the query/mutation/subscription keyword.<BR/>
	 * For instance:
	 * 
	 * <PRE>
	 * Character c = myQyeryType.execWithBindValues(
	 * 		"{hero(param:?heroParam) @include(if:true) {id name @skip(if: ?skip) appearsIn friends {id name}}}",
	 * 		"heroParam", heroParamValue, "skip", Boolean.FALSE);
	 * </PRE>
	 * 
	 * It offers a logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method is valid for queries/mutations/subscriptions which don't have bind variables, as there is no
	 * <I>parameters</I> argument to pass the list of values.
	 * 
	 * @param queryResponseDef
	 *            The response definition of the query, in the native GraphQL format (see here above). It must ommit the
	 *            query/mutation/subscription keyword, and start by the first { that follows.It may contain directives,
	 *            as explained in the GraphQL specs.
	 * @param paramsAndValues
	 *            This parameter contains all the name and values for the Bind Variables defined in the objectResponse
	 *            parameter, that must be sent to the server. Optional parameter may not have a value. They will be
	 *            ignored and not sent to the server. Mandatory parameter must be provided in this argument.<BR/>
	 *            This parameter contains an even number of parameters: it must be a series of name and values :
	 *            (paramName1, paramValue1, paramName2, paramValue2...)
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	public AnotherMutationTypeResponse exec(String queryResponseDef, Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of mutation {} ", queryResponseDef);
		ObjectResponse objectResponse = getResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return execWithBindValues(objectResponse, graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues));
	}

	/**
	 * This method is expected by the graphql-java framework. It will be called when this query is called. It offers a
	 * logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * Here is a sample (and please have a look to the GraphQL site for more information):
	 * 
	 * <PRE>
	 * ObjectResponse response;
	 * 
	 * public void setup() {
	 * 	// Preparation of the query
	 * 	objectResponse = myQueryType.getResponseBuilder()
	 * 			.withQueryResponseDef("{hero(param:?heroParam) @include(if:true) {id name @skip(if: ?skip) appearsIn friends {id name}}}").build();
	 * }
	 * 
	 * public void doTheJob() {
	 * ..
	 * Map<String, Object> params = new HashMap<>();
	 * params.put("heroParam", heroParamValue);
	 * params.put("skip", Boolean.FALSE);
	 * // This will set the value sinceValue to the sinceParam field parameter
	 * List<Board> boards = queryType.execWithBindValues(objectResponse, params);
	 * ...
	 * }
	 * </PRE>
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @param parameters
	 *            The list of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	public AnotherMutationTypeResponse execWithBindValues(ObjectResponse objectResponse, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException {
		if (logger.isTraceEnabled()) {
			if (parameters == null) {
				logger.trace("Executing of mutation without parameters");
			} else {
				StringBuffer sb = new StringBuffer("Executing of root mutation with parameters: ");
				boolean addComma = false;
				for (String key : parameters.keySet()) {
					sb.append(key).append(":").append(parameters.get(key));
					if (addComma)
						sb.append(", ");
					addComma = true;
				}
				logger.trace(sb.toString());
			}
		} else if (logger.isDebugEnabled()) {
			logger.debug("Executing of mutation 'AnotherMutationType'");
		}

		// Given values for the BindVariables
		parameters = (parameters != null) ? parameters : new HashMap<>();

		return configuration.getQueryExecutor().execute(objectResponse, parameters, AnotherMutationTypeResponse.class);
	}

	/**
	 * This method takes a predefined {@link ObjectResponse} as the definition for the GraphQL request, and executes the
	 * GraphQL request against the GraphQL server. It offers a logging of the call (if in debug mode), or of the call
	 * and its parameters (if in trace mode).<BR/>
	 * Here is a sample (and please have a look to the GraphQL site for more information):
	 * 
	 * <PRE>
	 * ObjectResponse response;
	 * 
	 * public void setup() {
	 * 	// Preparation of the query
	 * 	 objectResponse = myQueryType.getResponseBuilder()
	 * 			.withQueryResponseDef("{hero(param:?heroParam) @include(if:true) {id name @skip(if: ?skip) appearsIn friends {id name}}}").build();
	 * }
	 * 
	 * public void doTheJob() {
	 * ..
	 * // This will set the value sinceValue to the sinceParam field parameter
	 * List<Board> boards = queryType.exec(objectResponse, "heroParam", heroParamValue, "skip", Boolean.FALSE);
	 * ...
	 * }
	 * </PRE>
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @param paramsAndValues
	 *            This parameter contains all the name and values for the Bind Variables defined in the objectResponse
	 *            parameter, that must be sent to the server. Optional parameter may not have a value. They will be
	 *            ignored and not sent to the server. Mandatory parameter must be provided in this argument.<BR/>
	 *            This parameter contains an even number of parameters: it must be a series of name and values :
	 *            (paramName1, paramValue1, paramName2, paramValue2...)
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	public AnotherMutationTypeResponse exec(ObjectResponse objectResponse, Object... paramsAndValues)
			throws GraphQLRequestExecutionException {
		return execWithBindValues(objectResponse, graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues));
	}

	/**
	 * Get the {@link ObjectResponse.Builder} for the full query, as expected by the exec and execWithBindValues
	 * methods.
	 * 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public Builder getResponseBuilder() throws GraphQLRequestPreparationException {
		return new Builder(GraphQLRequest.class);
	}

	/**
	 * Get the {@link GraphQLRequest} for Full request. It's easier to directly execute:
	 * <PRE>
	 * GraphQLRequest request = new GraphQLRequest(fullRequest);
	 * </PRE>
	 * 
	 * @param fullRequest The full GraphQLRequest, as specified in the GraphQL specification
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public GraphQLRequest getGraphQLRequest(String fullRequest) throws GraphQLRequestPreparationException {
		return new GraphQLRequest(fullRequest);
	}

	/**
	 * This method executes a partial query against the GraphQL server. That is, the query that is one of the queries
	 * defined in the GraphQL query object. The queryResponseDef contains the part of the query that <B><U>is
	 * after</U></B> the query name.<BR/>
	 * For instance, if the query hero has one parameter (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * Map<String, Object> params = new HashMap<>();
	 * params.put("skip", Boolean.FALSE);
	 *
	 * Human c = myQyeryType.createHumanWithBindValues("{id name @skip(if: false) appearsIn friends {id name}}", human, params);
	 * </PRE>
	 * 
	 * It offers a logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method takes care of writing the query name, and the parameter(s) for the query. The given queryResponseDef
	 * describes the format of the response of the server response, that is the expected fields of the {@link Character}
	 * GraphQL type. It can be something like "{ id name }", if you want these fields of this type. Please take a look
	 * at the StarWars, Forum and other samples for more complex queries.<BR/>
	 * This method is valid for queries/mutations/subscriptions which don't have bind variables, as there is no
	 * <I>parameters</I> argument to pass the list of values.
	 * 
	 * @param queryResponseDef
	 *            The response definition of the query, in the native GraphQL format (see here above)
	 * @param human Parameter for the createHuman field of AnotherMutationType, as defined in the GraphQL schema
	 * @param parameters
	 *            The list of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	 @GraphQLNonScalar (fieldName = "createHuman", graphQLTypeName = "Human", javaClass = Human.class)
	public Human createHumanWithBindValues(String queryResponseDef, HumanInput human, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of query 'createHuman' in query mode: {} ", queryResponseDef);
		ObjectResponse objectResponse = getCreateHumanResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return createHuman(objectResponse, human, parameters);
	}

	/**
	 * This method executes a partial query against the GraphQL server. That is, the query that is one of the queries
	 * defined in the GraphQL query object. The queryResponseDef contains the part of the query that <B><U>is
	 * after</U></B> the query name.<BR/>
	 * For instance, if the query hero has one parameter (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * Human c = myQyeryType.createHuman("{id name @skip(if: false) appearsIn friends {id name}}", human, "skip", Boolean.FALSE);
	 * </PRE>
	 * 
	 * It offers a logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method takes care of writing the query name, and the parameter(s) for the query. The given queryResponseDef
	 * describes the format of the response of the server response, that is the expected fields of the {@link Character}
	 * GraphQL type. It can be something like "{ id name }", if you want these fields of this type. Please take a look
	 * at the StarWars, Forum and other samples for more complex queries.<BR/>
	 * This method is valid for queries/mutations/subscriptions which don't have bind variables, as there is no
	 * <I>parameters</I> argument to pass the list of values.
	 * 
	 * @param queryResponseDef
	 *            The response definition of the query, in the native GraphQL format (see here above)
	 * @param human Parameter for the createHuman field of AnotherMutationType, as defined in the GraphQL schema
	 * @param parameters
	 *            The list of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	 @GraphQLNonScalar (fieldName = "createHuman", graphQLTypeName = "Human", javaClass = Human.class)
	public Human createHuman(String queryResponseDef, HumanInput human, Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of query 'createHuman' in query mode: {} ", queryResponseDef);
		ObjectResponse objectResponse = getCreateHumanResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return createHumanWithBindValues(objectResponse, human, graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues));
	}

	/**
	 * This method is expected by the graphql-java framework. It will be called when this query is called. It offers a
	 * logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method is valid for queries/mutations/subscriptions which don't have bind variables, as there is no
	 * <I>parameters</I> argument to pass the list of values.<BR/>
	 * Here is a sample:
	 * 
	 * <PRE>
	 * ObjectResponse response;
	 * public void setup() {
	 * 	// Preparation of the query
	 * 	response = queryType.getBoardsResponseBuilder()
	 * 			.withQueryResponseDef("{id name publiclyAvailable topics(since:?sinceParam){id}}").build();
	 * }
	 * 
	 * public void doTheJob() {
	 * ..
	 * Map<String, Object> params = new HashMap<>();
	 * params.put("sinceParam", sinceValue);
	 * // This will set the value sinceValue to the sinceParam field parameter
	 * Human ret = queryType.createHumanWithBindValues(response, human, params);
	 * ...
	 * }
	 * </PRE>
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @param human Parameter for the createHuman field of AnotherMutationType, as defined in the GraphQL schema
	 * @param parameters
	 *            The list of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	 @GraphQLNonScalar (fieldName = "createHuman", graphQLTypeName = "Human", javaClass = Human.class)
	public Human createHumanWithBindValues(ObjectResponse objectResponse, HumanInput human, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException  {
		if (logger.isTraceEnabled()) {
			logger.trace("Executing of mutation 'createHuman' with parameters: {} ", human);
		} else if (logger.isDebugEnabled()) {
			logger.debug("Executing of mutation 'createHuman'");
		}
	
		// Given values for the BindVariables
		parameters = (parameters != null) ? parameters : new HashMap<>();
		parameters.put("anotherMutationTypeCreateHumanHuman", human);

		AnotherMutationTypeCreateHuman ret = configuration.getQueryExecutor().execute(objectResponse, parameters, AnotherMutationTypeCreateHuman.class);
		
		return ret.createHuman;
	}

	/**
	 * This method is expected by the graphql-java framework. It will be called when this query is called. It offers a
	 * logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method is valid for queries/mutations/subscriptions which don't have bind variables, as there is no
	 * <I>parameters</I> argument to pass the list of values.<BR/>
	 * Here is a sample:
	 * 
	 * <PRE>
	 * ObjectResponse response;
	 * public void setup() {
	 * 	// Preparation of the query
	 * 	response = queryType.getBoardsResponseBuilder()
	 * 			.withQueryResponseDef("{id name publiclyAvailable topics(since:?sinceParam){id}}").build();
	 * }
	 * 
	 * public void doTheJob() {
	 * ..
	 * // This will set the value sinceValue to the sinceParam field parameter
	 * Human ret = queryType.createHuman(response, human, "sinceParam", sinceValue);
	 * ...
	 * }
	 * </PRE>
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @param human Parameter for the createHuman field of AnotherMutationType, as defined in the GraphQL schema
	 * @param paramsAndValues
	 *            This parameter contains all the name and values for the Bind Variables defined in the objectResponse
	 *            parameter, that must be sent to the server. Optional parameter may not have a value. They will be
	 *            ignored and not sent to the server. Mandatory parameter must be provided in this argument.<BR/>
	 *            This parameter contains an even number of parameters: it must be a series of name and values :
	 *            (paramName1, paramValue1, paramName2, paramValue2...)
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	 @GraphQLNonScalar (fieldName = "createHuman", graphQLTypeName = "Human", javaClass = Human.class)
	public Human createHuman(ObjectResponse objectResponse, HumanInput human, Object... paramsAndValues)
			throws GraphQLRequestExecutionException  {
		if (logger.isTraceEnabled()) {
			StringBuffer sb = new StringBuffer();
			sb.append("Executing of query 'createHuman' with bind variables: ");
			boolean addComma = false;
			for (Object o : paramsAndValues) {
				if (o != null) {
					sb.append(o.toString());
					if (addComma)
						sb.append(", ");
					addComma = true;
				}
			}
			logger.trace(sb.toString());
		} else if (logger.isDebugEnabled()) {
			logger.debug("Executing of query 'createHuman' (with bind variables)");
		}

		Map<String, Object> bindVariableValues = graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues);
		bindVariableValues.put("anotherMutationTypeCreateHumanHuman", human);
		
		AnotherMutationTypeCreateHuman ret = configuration.getQueryExecutor().execute(objectResponse, bindVariableValues, AnotherMutationTypeCreateHuman.class);
		
		return ret.createHuman;
	}

	/**
	 * Get the {@link ObjectResponse.Builder} for the Human, as expected by the createHuman query.
	 * 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public Builder getCreateHumanResponseBuilder() throws GraphQLRequestPreparationException {
		return new Builder(GraphQLRequest.class, "createHuman", RequestType.mutation
			, InputParameter.newBindParameter("human","anotherMutationTypeCreateHumanHuman", true, null)
			);
	}


	/**
	 * Get the {@link GraphQLRequest} for the createHuman mutation, created with the given Partial request.
	 * 
	 * @param partialRequest
	 * 				The Partial GraphQLRequest, as explained in the 
	 * 				<A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/client.html">plugin client documentation</A> 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public GraphQLRequest getCreateHumanGraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return new GraphQLRequest(partialRequest, RequestType.mutation, "createHuman"
		, InputParameter.newBindParameter("human","anotherMutationTypeCreateHumanHuman", true, null)
		);
	}
	
	/**
	 * This method executes a partial query against the GraphQL server. That is, the query that is one of the queries
	 * defined in the GraphQL query object. The queryResponseDef contains the part of the query that <B><U>is
	 * after</U></B> the query name.<BR/>
	 * For instance, if the query hero has one parameter (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * Map<String, Object> params = new HashMap<>();
	 * params.put("skip", Boolean.FALSE);
	 *
	 * AllFieldCases c = myQyeryType.createAllFieldCasesWithBindValues("{id name @skip(if: false) appearsIn friends {id name}}", input, params);
	 * </PRE>
	 * 
	 * It offers a logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method takes care of writing the query name, and the parameter(s) for the query. The given queryResponseDef
	 * describes the format of the response of the server response, that is the expected fields of the {@link Character}
	 * GraphQL type. It can be something like "{ id name }", if you want these fields of this type. Please take a look
	 * at the StarWars, Forum and other samples for more complex queries.<BR/>
	 * This method is valid for queries/mutations/subscriptions which don't have bind variables, as there is no
	 * <I>parameters</I> argument to pass the list of values.
	 * 
	 * @param queryResponseDef
	 *            The response definition of the query, in the native GraphQL format (see here above)
	 * @param input Parameter for the createAllFieldCases field of AnotherMutationType, as defined in the GraphQL schema
	 * @param parameters
	 *            The list of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	 @GraphQLNonScalar (fieldName = "createAllFieldCases", graphQLTypeName = "AllFieldCases", javaClass = AllFieldCases.class)
	public AllFieldCases createAllFieldCasesWithBindValues(String queryResponseDef, AllFieldCasesInput input, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of query 'createAllFieldCases' in query mode: {} ", queryResponseDef);
		ObjectResponse objectResponse = getCreateAllFieldCasesResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return createAllFieldCases(objectResponse, input, parameters);
	}

	/**
	 * This method executes a partial query against the GraphQL server. That is, the query that is one of the queries
	 * defined in the GraphQL query object. The queryResponseDef contains the part of the query that <B><U>is
	 * after</U></B> the query name.<BR/>
	 * For instance, if the query hero has one parameter (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * AllFieldCases c = myQyeryType.createAllFieldCases("{id name @skip(if: false) appearsIn friends {id name}}", input, "skip", Boolean.FALSE);
	 * </PRE>
	 * 
	 * It offers a logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method takes care of writing the query name, and the parameter(s) for the query. The given queryResponseDef
	 * describes the format of the response of the server response, that is the expected fields of the {@link Character}
	 * GraphQL type. It can be something like "{ id name }", if you want these fields of this type. Please take a look
	 * at the StarWars, Forum and other samples for more complex queries.<BR/>
	 * This method is valid for queries/mutations/subscriptions which don't have bind variables, as there is no
	 * <I>parameters</I> argument to pass the list of values.
	 * 
	 * @param queryResponseDef
	 *            The response definition of the query, in the native GraphQL format (see here above)
	 * @param input Parameter for the createAllFieldCases field of AnotherMutationType, as defined in the GraphQL schema
	 * @param parameters
	 *            The list of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	 @GraphQLNonScalar (fieldName = "createAllFieldCases", graphQLTypeName = "AllFieldCases", javaClass = AllFieldCases.class)
	public AllFieldCases createAllFieldCases(String queryResponseDef, AllFieldCasesInput input, Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of query 'createAllFieldCases' in query mode: {} ", queryResponseDef);
		ObjectResponse objectResponse = getCreateAllFieldCasesResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return createAllFieldCasesWithBindValues(objectResponse, input, graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues));
	}

	/**
	 * This method is expected by the graphql-java framework. It will be called when this query is called. It offers a
	 * logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method is valid for queries/mutations/subscriptions which don't have bind variables, as there is no
	 * <I>parameters</I> argument to pass the list of values.<BR/>
	 * Here is a sample:
	 * 
	 * <PRE>
	 * ObjectResponse response;
	 * public void setup() {
	 * 	// Preparation of the query
	 * 	response = queryType.getBoardsResponseBuilder()
	 * 			.withQueryResponseDef("{id name publiclyAvailable topics(since:?sinceParam){id}}").build();
	 * }
	 * 
	 * public void doTheJob() {
	 * ..
	 * Map<String, Object> params = new HashMap<>();
	 * params.put("sinceParam", sinceValue);
	 * // This will set the value sinceValue to the sinceParam field parameter
	 * AllFieldCases ret = queryType.createAllFieldCasesWithBindValues(response, input, params);
	 * ...
	 * }
	 * </PRE>
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @param input Parameter for the createAllFieldCases field of AnotherMutationType, as defined in the GraphQL schema
	 * @param parameters
	 *            The list of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	 @GraphQLNonScalar (fieldName = "createAllFieldCases", graphQLTypeName = "AllFieldCases", javaClass = AllFieldCases.class)
	public AllFieldCases createAllFieldCasesWithBindValues(ObjectResponse objectResponse, AllFieldCasesInput input, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException  {
		if (logger.isTraceEnabled()) {
			logger.trace("Executing of mutation 'createAllFieldCases' with parameters: {} ", input);
		} else if (logger.isDebugEnabled()) {
			logger.debug("Executing of mutation 'createAllFieldCases'");
		}
	
		// Given values for the BindVariables
		parameters = (parameters != null) ? parameters : new HashMap<>();
		parameters.put("anotherMutationTypeCreateAllFieldCasesInput", input);

		AnotherMutationTypeCreateAllFieldCases ret = configuration.getQueryExecutor().execute(objectResponse, parameters, AnotherMutationTypeCreateAllFieldCases.class);
		
		return ret.createAllFieldCases;
	}

	/**
	 * This method is expected by the graphql-java framework. It will be called when this query is called. It offers a
	 * logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method is valid for queries/mutations/subscriptions which don't have bind variables, as there is no
	 * <I>parameters</I> argument to pass the list of values.<BR/>
	 * Here is a sample:
	 * 
	 * <PRE>
	 * ObjectResponse response;
	 * public void setup() {
	 * 	// Preparation of the query
	 * 	response = queryType.getBoardsResponseBuilder()
	 * 			.withQueryResponseDef("{id name publiclyAvailable topics(since:?sinceParam){id}}").build();
	 * }
	 * 
	 * public void doTheJob() {
	 * ..
	 * // This will set the value sinceValue to the sinceParam field parameter
	 * AllFieldCases ret = queryType.createAllFieldCases(response, input, "sinceParam", sinceValue);
	 * ...
	 * }
	 * </PRE>
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @param input Parameter for the createAllFieldCases field of AnotherMutationType, as defined in the GraphQL schema
	 * @param paramsAndValues
	 *            This parameter contains all the name and values for the Bind Variables defined in the objectResponse
	 *            parameter, that must be sent to the server. Optional parameter may not have a value. They will be
	 *            ignored and not sent to the server. Mandatory parameter must be provided in this argument.<BR/>
	 *            This parameter contains an even number of parameters: it must be a series of name and values :
	 *            (paramName1, paramValue1, paramName2, paramValue2...)
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	 @GraphQLNonScalar (fieldName = "createAllFieldCases", graphQLTypeName = "AllFieldCases", javaClass = AllFieldCases.class)
	public AllFieldCases createAllFieldCases(ObjectResponse objectResponse, AllFieldCasesInput input, Object... paramsAndValues)
			throws GraphQLRequestExecutionException  {
		if (logger.isTraceEnabled()) {
			StringBuffer sb = new StringBuffer();
			sb.append("Executing of query 'createAllFieldCases' with bind variables: ");
			boolean addComma = false;
			for (Object o : paramsAndValues) {
				if (o != null) {
					sb.append(o.toString());
					if (addComma)
						sb.append(", ");
					addComma = true;
				}
			}
			logger.trace(sb.toString());
		} else if (logger.isDebugEnabled()) {
			logger.debug("Executing of query 'createAllFieldCases' (with bind variables)");
		}

		Map<String, Object> bindVariableValues = graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues);
		bindVariableValues.put("anotherMutationTypeCreateAllFieldCasesInput", input);
		
		AnotherMutationTypeCreateAllFieldCases ret = configuration.getQueryExecutor().execute(objectResponse, bindVariableValues, AnotherMutationTypeCreateAllFieldCases.class);
		
		return ret.createAllFieldCases;
	}

	/**
	 * Get the {@link ObjectResponse.Builder} for the AllFieldCases, as expected by the createAllFieldCases query.
	 * 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public Builder getCreateAllFieldCasesResponseBuilder() throws GraphQLRequestPreparationException {
		return new Builder(GraphQLRequest.class, "createAllFieldCases", RequestType.mutation
			, InputParameter.newBindParameter("input","anotherMutationTypeCreateAllFieldCasesInput", true, null)
			);
	}


	/**
	 * Get the {@link GraphQLRequest} for the createAllFieldCases mutation, created with the given Partial request.
	 * 
	 * @param partialRequest
	 * 				The Partial GraphQLRequest, as explained in the 
	 * 				<A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/client.html">plugin client documentation</A> 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public GraphQLRequest getCreateAllFieldCasesGraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return new GraphQLRequest(partialRequest, RequestType.mutation, "createAllFieldCases"
		, InputParameter.newBindParameter("input","anotherMutationTypeCreateAllFieldCasesInput", true, null)
		);
	}
	
}
