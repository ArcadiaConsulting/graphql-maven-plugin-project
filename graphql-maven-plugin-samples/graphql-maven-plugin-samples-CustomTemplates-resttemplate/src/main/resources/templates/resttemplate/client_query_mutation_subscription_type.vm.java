package ${pluginConfiguration.packageName};
#macro(inputParams)#foreach ($inputParameter in $field.inputParameters), #if(${inputParameter.list})List<#end${inputParameter.type.classSimpleName}#if(${inputParameter.list})>#end ${inputParameter.javaName}#end#end

#macro(inputValues)#foreach ($inputParameter in $field.inputParameters), ${inputParameter.javaName}#end#end

import java.io.IOException;
import java.util.ArrayList;
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

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

#foreach($import in $imports)
import $import;
#end

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@Component
@GraphQLQuery(name = "${object.name}", type = RequestType.$type)
public class ${object.javaName} {

	/** Logger for this class */
	private static Logger logger = LoggerFactory.getLogger(${object.name}.class);

	final GraphqlClientUtils graphqlClientUtils = new GraphqlClientUtils();

	/**
	 * The rest client implementaiton 
	 */
	@Autowired
	@Qualifier("RestTemplateQueryExecutor")
	QueryExecutor executor;

	/**
	 * This constructor expects the URI of the GraphQL server. This constructor works only for http servers, not for
	 * https ones.<BR/>
	 * For example: http://my.server.com/graphql
	 * 
	 * @param graphqlEndpoint
	 *            the http URI for the GraphQL endpoint
	 */
	public ${object.javaName}() {
		new CustomScalarRegistryInitializer().initCustomScalarRegistry();
		new DirectiveRegistryInitializer().initDirectiveRegistry();
	}

#foreach ($field in $object.fields)
	/**
	 * This method is expected by the graphql-java framework. It will be called when this query is called. It offers a
	 * logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method takes care of writting the query name, and the parameter(s) for the query. The given queryResponseDef
	 * describes the format of the response of the server response, that is the expected fields of the {@link ${field.type.classSimpleName}}
	 * GraphQL type. It can be something like "{ id name }", if you want these fields of this type. Please take a look at
	 * the StarWars, Forum and other samples for more complex queries.<BR/>
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
	#if(${field.type.scalar}) @GraphQLScalar #else @GraphQLNonScalar #end(fieldName = "${field.name}", graphQLTypeName = "${field.graphQLTypeName}", javaClass = ${field.type.classSimpleName}.class)
	public #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end ${field.javaName}(String queryResponseDef#inputParams())
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of query '${field.name}' in query mode: {} ", queryResponseDef);
		ObjectResponse objectResponse = get${field.pascalCaseName}ResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return ${field.name}WithBindValues(objectResponse#inputValues(), null);
	}

	/**
	 * This method is expected by the graphql-java framework. It will be called when this query is called. It offers a
	 * logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method takes care of writting the query name, and the parameter(s) for the query. The given queryResponseDef
	 * describes the format of the response of the server response, that is the expected fields of the {@link ${field.type.classSimpleName}}
	 * GraphQL type. It can be something like "{ id name }", if you want these fields of this type. Please take a look at
	 * the StarWars, Forum and other samples for more complex queries.
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
	#if(${field.type.scalar}) @GraphQLScalar #else @GraphQLNonScalar #end(fieldName = "${field.name}", graphQLTypeName = "${field.graphQLTypeName}", javaClass = ${field.type.classSimpleName}.class)
	public #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end ${field.name}WithBindValues(String queryResponseDef#inputParams(), Map<String, Object> parameters)
			throws GraphQLRequestExecutionException, GraphQLRequestPreparationException {
		logger.debug("Executing of query '${field.name}' in query mode: {} ", queryResponseDef);
		ObjectResponse objectResponse = get${field.pascalCaseName}ResponseBuilder().withQueryResponseDef(queryResponseDef).build();
		return ${field.javaName}(objectResponse#inputValues(), parameters);
	}


	/**
	 * This method is expected by the graphql-java framework. It will be called when this query is called. It offers a
	 * logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).<BR/>
	 * This method is valid for queries/mutations/subscriptions which don't have bind variables, as there is no 
	 * <I>parameters</I> argument to pass the list of values.  
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
	#if(${field.type.scalar}) @GraphQLScalar #else @GraphQLNonScalar #end(fieldName = "${field.name}", graphQLTypeName = "${field.graphQLTypeName}", javaClass = ${field.type.classSimpleName}.class)
	public #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end ${field.javaName}(ObjectResponse objectResponse#inputParams())
			throws GraphQLRequestExecutionException  {
		return ${field.name}WithBindValues(objectResponse#inputValues(), null);
	}

	/**
	 * This method is expected by the graphql-java framework. It will be called when this query is called. It offers a
	 * logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).
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
	#if(${field.type.scalar}) @GraphQLScalar #else @GraphQLNonScalar #end(fieldName = "${field.name}", graphQLTypeName = "${field.graphQLTypeName}", javaClass = ${field.type.classSimpleName}.class)
	public #if(${field.list})List<#end${field.type.classSimpleName}#if(${field.list})>#end ${field.name}WithBindValues(ObjectResponse objectResponse#inputParams(), Map<String, Object> parameters)
			throws GraphQLRequestExecutionException  {
		if (logger.isTraceEnabled()) {
			logger.trace("Executing of $type '${field.name}' with parameters: #foreach ($inputParameter in $field.inputParameters){}#if($foreach.hasNext),#end #end"#foreach ($inputParameter in $field.inputParameters), ${inputParameter.javaName}#end);
		} else if (logger.isDebugEnabled()) {
			logger.debug("Executing of $type '${field.name}'");
		}
	
		// Given values for the BindVariables
		parameters = (parameters != null) ? parameters : new HashMap<>();
#foreach ($inputParameter in $field.inputParameters)
		parameters.put("${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", ${inputParameter.javaName});
#end

		${field.owningType.classSimpleName}${field.pascalCaseName} ret = executor.execute(objectResponse, parameters, ${field.owningType.classSimpleName}${field.pascalCaseName}.class);
		
		return ret.${field.javaName};
	}

	/**
	 * This method is expected by the graphql-java framework. It will be called when this query is called. It offers a
	 * logging of the call (if in debug mode), or of the call and its parameters (if in trace mode).
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
	#if(${field.type.scalar}) @GraphQLScalar #else @GraphQLNonScalar #end(fieldName = "${field.name}", graphQLTypeName = "${field.graphQLTypeName}", javaClass = ${field.type.classSimpleName}.class)
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

		Map<String, Object> bindVariableValues = graphqlClientUtils.generatesBindVariableValuesMap(paramsAndValues);
#foreach ($inputParameter in $field.inputParameters)
		bindVariableValues.put("${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", ${inputParameter.javaName});
#end
		
		${field.owningType.classSimpleName}${field.pascalCaseName} ret = executor.execute(objectResponse, bindVariableValues, ${field.owningType.classSimpleName}${field.pascalCaseName}.class);
		
		return ret.${field.javaName};
	}

	/**
	 * Get the {@link ObjectResponse.Builder} for the ${field.type.classSimpleName}, as expected by the ${field.name} query.
	 * 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public Builder get${field.pascalCaseName}ResponseBuilder() throws GraphQLRequestPreparationException {
		return new Builder(GraphQLRequest.class, "${field.name}", RequestType.$type
#foreach ($inputParameter in $field.inputParameters)
			, InputParameter.newBindParameter("${inputParameter.name}","${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", ${inputParameter.mandatory}, null)
#end
			);
	}

	/**
	 * Get the {@link GraphQLRequest} for the ${field.name} $type, created with the given Partial request.
	 * 
	 * @param partialRequest
	 * 				The Partial GraphQLRequest, as explained in the 
	 * 				<A HREF="https://graphql-maven-plugin-project.graphql-java-generator.com/client.html">plugin client documentation</A> 
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public GraphQLRequest get${field.pascalCaseName}GraphQLRequest(String partialRequest) throws GraphQLRequestPreparationException {
		return new GraphQLRequest(partialRequest, RequestType.$type, "${field.name}"
#foreach ($inputParameter in $field.inputParameters)
		, InputParameter.newBindParameter("${inputParameter.name}","${object.camelCaseName}${field.pascalCaseName}${inputParameter.pascalCaseName}", ${inputParameter.mandatory}, null)
#end
		);
	}

#end
}
