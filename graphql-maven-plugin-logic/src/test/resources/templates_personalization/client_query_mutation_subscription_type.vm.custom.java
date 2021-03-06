/** This template is custom **/
package ${pluginConfiguration.packageName};
#macro(inputParams)#foreach ($inputParameter in $field.inputParameters), #if(${inputParameter.list})List<#end${inputParameter.type.classSimpleName}#if(${inputParameter.list})>#end ${inputParameter.javaName}#end#end

#macro(inputValues)#foreach ($inputParameter in $field.inputParameters), ${inputParameter.javaName}#end#end

import java.io.IOException;
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
import com.graphql_java_generator.client.GraphqlClientUtils;
import com.graphql_java_generator.client.QueryExecutor;
import com.graphql_java_generator.client.QueryExecutorImpl;
import com.graphql_java_generator.client.request.Builder;
import com.graphql_java_generator.client.request.InputParameter;
import com.graphql_java_generator.client.request.ObjectResponse;
import com.graphql_java_generator.customscalars.GraphQLScalarTypeDate;
import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

import ${pluginConfiguration.packageName}.DirectiveRegistryInitializer;

#foreach($import in $imports)
import $import;
#end

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
public class ${object.javaName} {

	/** Logger for this class */
	private static Logger logger = LoggerFactory.getLogger(${object.name}.class);

	final GraphqlClientUtils graphqlClientUtils = new GraphqlClientUtils();

	final QueryExecutor executor;

	/**
	 * This constructor expects the URI of the GraphQL server. This constructor works only for http servers, not for
	 * https ones.<BR/>
	 * For example: http://my.server.com/graphql
	 * 
	 * @param graphqlEndpoint
	 *            the http URI for the GraphQL endpoint
	 */
	public ${object.javaName}(String graphqlEndpoint) {
		this.executor = new QueryExecutorImpl(graphqlEndpoint);
		new CustomScalarRegistryInitializer().initCustomScalarRegistry();
		new DirectiveRegistryInitializer().initDirectiveRegistry();
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
	public ${object.javaName}(String graphqlEndpoint, SSLContext sslContext, HostnameVerifier hostnameVerifier) {
		this.executor = new QueryExecutorImpl(graphqlEndpoint, sslContext, hostnameVerifier);
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
	public ${object.name}(String graphqlEndpoint, Client client, ObjectMapper objectMapper) {
		this.executor = new QueryExecutorImpl(graphqlEndpoint, client, objectMapper);
		new CustomScalarRegistryInitializer().initCustomScalarRegistry();
	}

	/**
	 * This method takes a full query definition, and executes the GraphQL request against the GraphQL server. That is,
	 * the query contains the full string that <B><U>follows</U></B> the query/mutation/subscription keyword.<BR/>
	 * For instance:
	 * 
	 * <PRE>
	 * Character c = myQyeryType.exec(
	 * 		"{hero(param: \"my param\") @include(if:true) {id name @skip(if: false) appearsIn friends {id name}}}");
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
	 * @throws IOException
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@GraphQLQuery
	public ${object.javaName}Response exec(String queryResponseDef)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of ${object.requestType} {} ", queryResponseDef);
		ObjectResponse objectResponse = getResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return execWithBindValues(objectResponse, null);
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
	 *            The response definition of the ${object.requestType}, in the native GraphQL format (see here above). It must ommit the
	 *            query/mutation/subscription keyword, and start by the first { that follows.It may contain directives,
	 *            as explained in the GraphQL specs.
	 * @param parameters
	 *            The map of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}. The key is the parameter name, as
	 *            defined in the query (in the above sample: heroParam is an optional parameter and skip is a mandatory
	 *            one). The value is the parameter vale in its Java type (for instance a {@link Date} for the
	 *            {@link GraphQLScalarTypeDate}). The parameters which value is missing in this map will no be
	 *            transmitted toward the GraphQL server.
	 * @throws IOException
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@GraphQLQuery
	public ${object.javaName}Response execWithBindValues(String queryResponseDef, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of ${object.requestType} {} ", queryResponseDef);
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
	 * @throws IOException
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@GraphQLQuery
	public ${object.javaName}Response exec(String queryResponseDef, Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of ${object.requestType} {} ", queryResponseDef);
		ObjectResponse objectResponse = getResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return execWithBindValues(objectResponse, graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues));
	}

	/**
	 * This method takes a prepared {@link ObjectResponse} as the definition for the GraphQL request, and executes the
	 * GraphQL request against the GraphQL server. This is the recommended way to call a GraphQL server, with a prepared
	 * query. <BR/>
	 * Here is a sample (and please have a look to the GraphQL site for more information):
	 * 
	 * <PRE>
	 * public void setup() {
	 * 	// Preparation of the query
	 * 	ObjectResponse objectResponse = myQueryType.getResponseBuilder()
	 * 			.withQueryResponseDef("{hero @include(if:true) {id name appearsIn friends {id name}}}").build();
	 * }
	 * 
	 * public void doTheJob() {
	 * ..
	 * List<Board> boards = queryType.exec(objectResponse);
	 * ...
	 * }
	 * </PRE>
	 * 
	 * It offers a logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method is valid for queries/mutations/subscriptions which don't have bind variables, as there is no
	 * <I>parameters</I> argument to pass the list of values.
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
	 * @throws IOException
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@GraphQLQuery
	public ${object.javaName}Response exec(ObjectResponse objectResponse) throws GraphQLRequestExecutionException {
		return execWithBindValues(objectResponse, null);
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
	 * @throws IOException
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@GraphQLQuery
	public ${object.javaName}Response execWithBindValues(ObjectResponse objectResponse, Map<String, Object> parameters)
			throws GraphQLRequestExecutionException {
		if (logger.isTraceEnabled()) {
			if (parameters == null) {
				logger.trace("Executing of ${object.requestType} without parameters");
			} else {
				StringBuffer sb = new StringBuffer("Executing of root ${object.requestType} with parameters: ");
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
			logger.debug("Executing of ${object.requestType} '${object.name}'");
		}

		if (!${object.javaName}Response.class.equals(objectResponse.getFieldClass())) {
			throw new GraphQLRequestExecutionException("The ObjectResponse parameter should be an instance of "
					+ ${object.javaName}Response.class + ", but is an instance of " + objectResponse.getClass().getName());
		}

		// Given values for the BindVariables
		parameters = (parameters != null) ? parameters : new HashMap<>();

		return executor.execute("${object.requestType}", objectResponse, parameters, ${object.javaName}Response.class);
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
	 * @throws IOException
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@GraphQLQuery
	public ${object.javaName}Response exec(ObjectResponse objectResponse, Object... paramsAndValues)
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
		return new Builder(${object.javaName}RootResponse.class, "${object.requestType}", true);
	}

#foreach ($field in $object.fields)
	/**
	 * This method executes a partial query against the GraphQL server. That is, the query that is one of the queries
	 * defined in the GraphQL query object. The queryResponseDef contains the part of the query that <B><U>is
	 * after</U></B> the query name.<BR/>
	 * For instance, if the query hero has one parameter (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end c = myQyeryType.${field.javaName}("{id name @skip(if: false) appearsIn friends {id name}}"#inputValues, heroParamValue);
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
#foreach ($inputParameter in $field.inputParameters)
	 * @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
	 * @throws IOException
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@GraphQLNonScalar(graphQLTypeName = "${field.graphQLTypeName}", javaClass = ${field.type.classSimpleName}.class)
	@GraphQLQuery
	public #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end ${field.javaName}(String queryResponseDef#inputParams())
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of query '${field.name}' in query mode: {} ", queryResponseDef);
		ObjectResponse objectResponse = get${field.pascalCaseName}ResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return ${field.name}WithBindValues(objectResponse#inputValues(), null);
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
	 * #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end c = myQyeryType.${field.name}WithBindValues("{id name @skip(if: false) appearsIn friends {id name}}"#inputValues, params);
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
#foreach ($inputParameter in $field.inputParameters)
	 * @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
	 * @param parameters
	 *            The list of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @throws IOException
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@GraphQLNonScalar(graphQLTypeName = "${field.graphQLTypeName}", javaClass = ${field.type.classSimpleName}.class)
	@GraphQLQuery
	public #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end ${field.name}WithBindValues(String queryResponseDef#inputParams(), Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of query '${field.name}' in query mode: {} ", queryResponseDef);
		ObjectResponse objectResponse = get${field.pascalCaseName}ResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return ${field.javaName}(objectResponse#inputValues(), parameters);
	}

	/**
	 * This method executes a partial query against the GraphQL server. That is, the query that is one of the queries
	 * defined in the GraphQL query object. The queryResponseDef contains the part of the query that <B><U>is
	 * after</U></B> the query name.<BR/>
	 * For instance, if the query hero has one parameter (as defined in the GraphQL schema):
	 * 
	 * <PRE>
	 * #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end c = myQyeryType.${field.name}("{id name @skip(if: false) appearsIn friends {id name}}"#inputValues, "skip", Boolean.FALSE);
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
#foreach ($inputParameter in $field.inputParameters)
	 * @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
	 * @param parameters
	 *            The list of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @throws IOException
	 * @throws GraphQLRequestPreparationException
	 *             When an error occurs during the request preparation, typically when building the
	 *             {@link ObjectResponse}
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@GraphQLNonScalar(graphQLTypeName = "${field.graphQLTypeName}", javaClass = ${field.type.classSimpleName}.class)
	@GraphQLQuery
	public #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end ${field.name}(String queryResponseDef#inputParams(), Object... paramsAndValues)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of query '${field.name}' in query mode: {} ", queryResponseDef);
		ObjectResponse objectResponse = get${field.pascalCaseName}ResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return ${field.javaName}WithBindValues(objectResponse#inputValues(), graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues));
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
	 * 
	 * public void setup() {
	 * 	// Preparation of the query
	 * 	 response = queryType.getBoardsResponseBuilder()
	 * 			.withQueryResponseDef("{id name publiclyAvailable }").build();
	 * }
	 * 
	 * public void doTheJob() {
	 * ..
	 * // This will set the value sinceValue to the sinceParam field parameter
	 * #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end c = queryType.${field.javaName}(response#inputValues);
	 * ...
	 * }
	 * </PRE> 
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
#foreach ($inputParameter in $field.inputParameters)
	 * @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
	 * @throws IOException
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@GraphQLNonScalar(graphQLTypeName = "${field.graphQLTypeName}", javaClass = ${field.type.classSimpleName}.class)
	@GraphQLQuery
	public #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end ${field.javaName}(ObjectResponse objectResponse#inputParams())
			throws GraphQLRequestExecutionException  {
		return ${field.name}WithBindValues(objectResponse#inputValues(), null);
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
	 * #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end ret = queryType.${field.name}WithBindValues(response#inputValues, params);
	 * ...
	 * }
	 * </PRE>
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
#foreach ($inputParameter in $field.inputParameters)
	 * @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
	 * @param parameters
	 *            The list of values, for the bind variables defined in the query. If there is no bind variable in the
	 *            defined Query, this argument may be null or an empty {@link Map}
	 * @throws IOException
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@GraphQLNonScalar(graphQLTypeName = "${field.graphQLTypeName}", javaClass = ${field.type.classSimpleName}.class)
	@GraphQLQuery
	public #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end ${field.name}WithBindValues(ObjectResponse objectResponse#inputParams(), Map<String, Object> parameters)
			throws GraphQLRequestExecutionException  {
		if (logger.isTraceEnabled()) {
			logger.trace("Executing of ${object.requestType} '${field.name}' with parameters: #foreach ($inputParameter in $field.inputParameters){}#if($foreach.hasNext),#end #end"#foreach ($inputParameter in $field.inputParameters), ${inputParameter.javaName}#end);
		} else if (logger.isDebugEnabled()) {
			logger.debug("Executing of ${object.requestType} '${field.name}'");
		}
	
		// Given values for the BindVariables
		parameters = (parameters != null) ? parameters : new HashMap<>();
#foreach ($inputParameter in $field.inputParameters)
		parameters.put("${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", ${inputParameter.javaName});
#end

		if (!${field.type.classSimpleName}.class.equals(objectResponse.getFieldClass())) {
			throw new GraphQLRequestExecutionException("The ObjectResponse parameter should be an instance of "
					+ ${field.type.classSimpleName}.class + ", but is an instance of " + objectResponse.getClass().getName());
		}

		${field.owningType.classSimpleName}${field.pascalCaseName} ret = executor.execute("${object.requestType}", objectResponse, parameters, ${field.owningType.classSimpleName}${field.pascalCaseName}.class);
		
		return ret.${field.javaName};
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
	 * #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end ret = queryType.${field.javaName}(response#inputValues, "sinceParam", sinceValue);
	 * ...
	 * }
	 * </PRE>
	 * 
	 * @param objectResponse
	 *            The definition of the response format, that describes what the GraphQL server is expected to return
#foreach ($inputParameter in $field.inputParameters)
	 * @param ${inputParameter.name} Parameter for the ${field.name} field of ${object.name}, as defined in the GraphQL schema
#end
	 * @param paramsAndValues
	 *            This parameter contains all the name and values for the Bind Variables defined in the objectResponse
	 *            parameter, that must be sent to the server. Optional parameter may not have a value. They will be
	 *            ignored and not sent to the server. Mandatory parameter must be provided in this argument.<BR/>
	 *            This parameter contains an even number of parameters: it must be a series of name and values :
	 *            (paramName1, paramValue1, paramName2, paramValue2...)
	 * @throws IOException
	 * @throws GraphQLRequestExecutionException
	 *             When an error occurs during the request execution, typically a network error, an error from the
	 *             GraphQL server or if the server response can't be parsed
	 */
	@GraphQLNonScalar(graphQLTypeName = "${field.graphQLTypeName}", javaClass = ${field.type.classSimpleName}.class)
	@GraphQLQuery
	public #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end ${field.javaName}(ObjectResponse objectResponse#inputParams(), Object... paramsAndValues)
			throws GraphQLRequestExecutionException  {
		if (logger.isTraceEnabled()) {
			StringBuffer sb = new StringBuffer();
			sb.append("Executing of query '${field.name}' with bind variables: ");
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
			logger.debug("Executing of query '${field.name}' (with bind variables)");
		}

		if (!${field.type.classSimpleName}.class.equals(objectResponse.getFieldClass())) {
			throw new GraphQLRequestExecutionException("The ObjectResponse parameter should be an instance of "
					+ ${field.type.classSimpleName}.class + ", but is an instance of " + objectResponse.getClass().getName());
		}

		Map<String, Object> bindVariableValues = graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues);
#foreach ($inputParameter in $field.inputParameters)
		bindVariableValues.put("${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", ${inputParameter.javaName});
#end
		
		${field.owningType.classSimpleName}${field.pascalCaseName} ret = executor.execute("${object.requestType}", objectResponse, bindVariableValues, ${field.owningType.classSimpleName}${field.pascalCaseName}.class);
		
		return ret.${field.javaName};
	}

	/**
	 * Get the {@link ObjectResponse.Builder} for the ${field.type.classSimpleName}, as expected by the ${field.name} query.
	 * 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public Builder get${field.pascalCaseName}ResponseBuilder() throws GraphQLRequestPreparationException {
		Builder builder = new Builder(getClass(), "${field.name}");
#foreach ($inputParameter in $field.inputParameters)
		builder.withInputParameter(InputParameter.newBindParameter("${inputParameter.name}","${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", false, null));
#end
		return builder;
	}
	
#end
}
