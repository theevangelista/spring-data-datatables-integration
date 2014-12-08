spring-data-datatables-integration
==================================

A simple integration using Spring Data JPA Pages and Datatables v1.10+. Inspired by Dandelion Datatables

##Simple Usage
With your Spring Data and repositories configured you just need pass parameters!
Data supports JPA Criteria API and QueryDSL making the search easy just extends you repository to each one you need
`QueryDslPredicateExecutor<Foo>` or ` JpaSpecificationExecutor<Foo>`


###On a controller
```java

   @RequestMapping(value = "/getTable", method = RequestMethod.GET)
   @ResponseBody
    public PageEntity<Foo> getData(@DatatablesParams DatatablesMetadata metadata) {
        Page<Foo> page = fooService.findAll(metadata.getCurrentPageIndex(), metadata.getLenght(), metadata.getSearch());
        return new PageEntity<>(metadata.getDraw(), page.getNumberOfElements(), page.getTotalElements(), page.getContent());
    }

```

###On the service layer
```java

    public Page<Foo> findAll(int currentPageIndex, int lenght, String search) {
        return fooRepository.findAll(FooPredicate.searchTerm(search), new PageRequest(currentPageIndex, lenght));
    }

```

*Note: FooPredicate is a class with a method returning a Predicate (QueryDSL) type, it can be switched to JPA Criteria API*
