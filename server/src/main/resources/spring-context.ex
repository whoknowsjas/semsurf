

	<!-- 
	<bean id="neo4jTransactionManagerService"
		class="org.neo4j.kernel.impl.transaction.SpringTransactionManager">
		<constructor-arg ref="graphDb" />
	</bean>
	<bean id="neo4jUserTransactionService" class="org.neo4j.kernel.impl.transaction.UserTransactionImpl">
		<constructor-arg ref="graphDb" />
	</bean>

	<bean id="neo4jTransactionManager"
		class="org.springframework.transaction.jta.JtaTransactionManager">
		<property name="transactionManager" ref="neo4jTransactionManagerService" />
		<property name="userTransaction" ref="neo4jUserTransactionService" />
	</bean>
	-->



<context:annotation-config/>
    <context:spring-configured/>
 	
 	<bean id="neo4jTransactionManagerService"
	    class="org.neo4j.kernel.impl.transaction.SpringTransactionManager">
	    <constructor-arg ref="gds" />
	</bean>
	
	<bean id="neo4jUserTransactionService" class="org.neo4j.kernel.impl.transaction.UserTransactionImpl">
	    <constructor-arg ref="gds" />
	</bean>
	
	<bean id="neo4jTransactionManager"
	    class="org.springframework.transaction.jta.JtaTransactionManager">
	    <property name="transactionManager" ref="neo4jTransactionManagerService" />
	    <property name="userTransaction" ref="neo4jUserTransactionService" />
	</bean>
	
	<tx:annotation-driven transaction-manager="neo4jTransactionManager" />
	
	<neo4j:repositories base-package="com.ost.semsurf.repository" />
	 
	<neo4j:config graphDatabaseService="gds" />
	
	<bean id="gds" class="org.neo4j.kernel.EmbeddedGraphDatabase" destroy-method="shutdown">
		<constructor-arg index="0" value="/tmp/gdb" />
		<constructor-arg index="1">
			<map><entry key="enable_remote_shell" value="true"/></map>
		</constructor-arg>
	</bean>

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:neo4j="http://www.springframework.org/schema/data/neo4j"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
						http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
						http://www.springframework.org/schema/context 
						http://www.springframework.org/schema/context/spring-context-3.0.xsd
						http://www.springframework.org/schema/data/neo4j
						http://www.springframework.org/schema/data/neo4j/spring-neo4j-2.0.xsd
						http://www.springframework.org/schema/tx 
						http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
">
 
 	<context:annotation-config/>
    <context:spring-configured/>
 	<tx:annotation-driven mode="aspectj" transaction-manager="transactionManager"/>
 	
	<neo4j:repositories base-package="com.ost.semsurf.repository" />
	<neo4j:config graphDatabaseService="gds" />

	<bean id="gds" class="org.neo4j.kernel.EmbeddedGraphDatabase" destroy-method="shutdown">
		<constructor-arg index="0" value="data/semsurf.db" />
		<constructor-arg index="1">
			<map><entry key="enable_remote_shell" value="true"/></map>
		</constructor-arg>
	</bean>
	
  
 
</beans>