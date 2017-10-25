package com.daniel.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	// 下面对上面的UserRepository做一些解释，该接口继承自JpaRepository，通过查看JpaRepository接口的API文档，
	// 可以看到该接口本身已经实现了创建（save）、更新（save）、删除（delete）、查询（findAll、findOne）等基本操作的函数，
	// 因此对于这些基础操作的数据访问就不需要开发者再自己定义。
	
	
	//	在上例中，我们可以看到下面两个函数：
	//
	//	User findByName(String name)
	//	User findByNameAndAge(String name, Integer age)
	//	它们分别实现了按name查询User实体和按name和age查询User实体，可以看到我们这里没有任何类SQL语句就完成了两个条件查询方法。这就是Spring-data-jpa的一大特性：通过解析方法名创建查询。
	//
	//	除了通过解析方法名来创建查询外，它也提供通过使用@Query 注解来创建查询，您只需要编写JPQL语句，并通过类似“:name”来映射@Param指定的参数，就像例子中的第三个findUser函数一样。

	User findByName(String name);

	User findByNameAndAge(String name, Integer age);

	@Query("from User u where u.name=:name")
	User findUser(@Param("name") String name);

}
