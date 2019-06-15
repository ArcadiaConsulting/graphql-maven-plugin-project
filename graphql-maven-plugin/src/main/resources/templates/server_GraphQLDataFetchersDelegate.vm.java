package ${package};

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import graphql.schema.DataFetchingEnvironment;

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
public interface ${dataFetcherDelegate.pascalCaseName} {

#foreach ($dataFetcher in $dataFetcherDelegate.dataFetchers)
	/**
	 * This method loads the data for ${dataFetcher.field.owningType.name}.${dataFetcher.field.name}
	 * <BR/>
	 * Actual execution of the DataFetcher. This is delegated to the developper, as it is not possible to manage every possible use cases.<BR/>
	 * Note 1: In the future, more and more standard cases will be generated.
	 * 
	 * @param dataFetchingEnvironment The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher 
#if($dataFetcher.sourceName)
	 * @param source The object from which the field is fetch. It typically contains the id to use in the query.
#end
#foreach($argument in $dataFetcher.field.inputParameters)
	 * @param ${argument.camelCaseName} The input parameter sent in the query by the GraphQL consumer
#end
	 * @throws NoSuchElementException This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 * by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 * wheter or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public #if(${dataFetcher.field.list})List<#end${dataFetcher.field.type.classSimpleName}#if(${dataFetcher.field.list})>#end ${dataFetcher.camelCaseName}(DataFetchingEnvironment dataFetchingEnvironment#if($dataFetcher.sourceName), ${dataFetcher.sourceName} source#end#foreach($argument in $dataFetcher.field.inputParameters), ${argument.type.classSimpleName} ${argument.camelCaseName}#end);
	
#end
}
