package com.kyrosoft.ims.service.impl;

import com.kyrosoft.ims.model.*;
import com.kyrosoft.ims.model.dto.*;
import com.kyrosoft.ims.service.*;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.util.List;

@Service
@Transactional
public class BaseSearchServiceImpl implements BaseSearchService {

    @PersistenceContext
    protected EntityManager entityManager;

    protected BaseSearchServiceImpl() {}

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    private static boolean containsColumn(String column, Class<?> type) {
        if (column.contains(".")) {
            // JPA does not support complex sorting scheme.
            return false;
        } else {
            while (null != type) {
                Field[] fields = type.getDeclaredFields();
                for (Field field : fields) {
                    if (field.getName().equalsIgnoreCase(column)) {
                        return true;
                    }
                }
                type = type.getSuperclass();
            }
        }
        return false;
    }

    public SearchResult<Employee> search(EmployeeSearchCriteria criteria) throws Exception {

        // Set default sorting option
        // if sortBy is null/empty, or is not a field of the specified type, sort by id
        if (!StringUtils.hasText(criteria.getSortBy()) || !containsColumn(criteria.getSortBy(), Employee.class)) {
            criteria.setSortBy("id");
        }
        if (criteria.getSortType() == null) {
            criteria.setSortType(SortType.ASC);
        }

        String className = Employee.class.getName();

        try {
            StringBuffer sb = new StringBuffer("SELECT e FROM ").append(className).append(" e ");
            // Generate and append WHERE clause
            sb.append(generateWhereClause(criteria));
            // Append ORDER BY clause
            sb.append(" ORDER BY ").append(criteria.getSortBy()).append(" ").append(criteria.getSortType().name());

            // Create query
            TypedQuery<Employee> query = getEntityManager().createQuery(sb.toString(), Employee.class);

            // Populate query parameters
            populateQueryParameters(query, criteria);

            // set paging options
            if (criteria.getPageNumber() > 0) {
                query.setMaxResults(criteria.getPageSize());
                query.setFirstResult((criteria.getPageNumber() - 1) * criteria.getPageSize());
            }

            // Execute the query
            List<Employee> entities = query.getResultList();

            SearchResult<Employee> result = new SearchResult<Employee>();
            result.setValues(entities);

            if (criteria.getPageNumber() > 0) {
                sb.delete(0, sb.length());
                // get total page count
                sb.append("SELECT COUNT(e) FROM ").append(className).append(" e ");
                sb.append(generateWhereClause(criteria));

                // Create query
                TypedQuery<Long> countQuery = getEntityManager().createQuery(sb.toString(), Long.class);

                populateQueryParameters(countQuery, criteria);

                int totalCount = countQuery.getSingleResult().intValue();
                int totalPageCount = (totalCount + criteria.getPageSize() - 1) / criteria.getPageSize();
                result.setTotal(totalCount);
                result.setTotalPages(totalPageCount);
                result.setPageSize(criteria.getPageSize());
                result.setPageNumber(criteria.getPageNumber());
            } else {
                result.setTotal(entities.size());
                result.setTotalPages(1);
                result.setPageSize(entities.size());
                result.setPageNumber(1);
            }

            // set sortBy and sortType for result
            result.setSortBy(criteria.getSortBy());
            result.setSortType(criteria.getSortType());

            return result;
        } catch (Exception e) {
            throw new Exception();
        }
    }

    protected String generateWhereClause(EmployeeSearchCriteria criteria) throws Exception {
        StringBuffer sb = new StringBuffer("WHERE (1=1)");

        if (criteria.getNik_eq() != null) {
            sb.append(" AND e.nik = :nik_eq");
        }

        if (criteria.getNik_like() != null) {
            sb.append(" AND e.nik LIKE :nik_like");
        }

        if (criteria.getUsername_eq() != null) {
            sb.append(" AND e.username = :username_eq");
        }

        if (criteria.getUsername_like() != null) {
            sb.append(" AND e.username LIKE :username_like");
        }

        if (criteria.getFirstname_eq() != null) {
            sb.append(" AND e.firstname = :firstname_eq");
        }

        if (criteria.getFirstname_like() != null) {
            sb.append(" AND e.firstname LIKE :firstname_like");
        }

        if (criteria.getLastname_eq() != null) {
            sb.append(" AND e.lastname = :lastname_eq");
        }

        if (criteria.getLastname_like() != null) {
            sb.append(" AND e.lastname LIKE :lastname_like");
        }

        if (criteria.getMobile_eq() != null) {
            sb.append(" AND e.mobile = :mobile_eq");
        }

        if (criteria.getMobile_like() != null) {
            sb.append(" AND e.mobile LIKE :mobile_like");
        }

        if (criteria.getAddress_eq() != null) {
            sb.append(" AND e.address = :address_eq");
        }

        if (criteria.getAddress_like() != null) {
            sb.append(" AND e.address LIKE :address_like");
        }

        if (criteria.getYearStart_eq() != null) {
            sb.append(" AND e.yearStart = :yearStart_eq");
        }

        if (criteria.getYearStart_ge() != null) {
            sb.append(" AND e.yearStart >= :yearStart_ge");
        }

        if (criteria.getYearStart_gt() != null) {
            sb.append(" AND e.yearStart > :yearStart_gt");
        }

        if (criteria.getYearStart_le() != null) {
            sb.append(" AND e.yearStart <= :yearStart_le");
        }

        if (criteria.getYearStart_lt() != null) {
            sb.append(" AND e.yearStart < :yearStart_lt");
        }

        if (criteria.getSalary_eq() != null) {
            sb.append(" AND e.salary = :salary_eq");
        }

        if (criteria.getSalary_ge() != null) {
            sb.append(" AND e.salary >= :salary_ge");
        }

        if (criteria.getSalary_gt() != null) {
            sb.append(" AND e.salary > :salary_gt");
        }

        if (criteria.getSalary_le() != null) {
            sb.append(" AND e.salary <= :salary_le");
        }

        if (criteria.getSalary_lt() != null) {
            sb.append(" AND e.salary < :salary_lt");
        }

        if (criteria.getIsAdmin_eq() != null) {
            sb.append(" AND e.isAdmin = :isAdmin_eq");
        }

        if (criteria.getLastLogin_eq() != null) {
            sb.append(" AND e.lastLogin = :lastLogin_eq");
        }

        if (criteria.getLastLogin_ge() != null) {
            sb.append(" AND e.lastLogin >= :lastLogin_ge");
        }

        if (criteria.getLastLogin_gt() != null) {
            sb.append(" AND e.lastLogin > :lastLogin_gt");
        }

        if (criteria.getLastLogin_le() != null) {
            sb.append(" AND e.lastLogin <= :lastLogin_le");
        }

        if (criteria.getLastLogin_lt() != null) {
            sb.append(" AND e.lastLogin < :lastLogin_lt");
        }

        if (criteria.getEmployeeLevel_eq() != null) {
            sb.append(" AND e.employeeLevel = :employeeLevel_eq");
        }

        if (criteria.getId_eq() != null) {
            sb.append(" AND e.id = :id_eq");
        }

        if (criteria.getId_ge() != null) {
            sb.append(" AND e.id >= :id_ge");
        }

        if (criteria.getId_gt() != null) {
            sb.append(" AND e.id > :id_gt");
        }

        if (criteria.getId_le() != null) {
            sb.append(" AND e.id <= :id_le");
        }

        if (criteria.getId_lt() != null) {
            sb.append(" AND e.id < :id_lt");
        }

        return sb.toString();
    }

    protected void populateQueryParameters(Query query, EmployeeSearchCriteria criteria) throws Exception {

        if (criteria.getNik_eq() != null) {
            query.setParameter("nik_eq", criteria.getNik_eq());
        }

        if (criteria.getNik_like() != null) {
            query.setParameter("nik_like", criteria.getNik_like());
        }

        if (criteria.getUsername_eq() != null) {
            query.setParameter("username_eq", criteria.getUsername_eq());
        }

        if (criteria.getUsername_like() != null) {
            query.setParameter("username_like", criteria.getUsername_like());
        }

        if (criteria.getFirstname_eq() != null) {
            query.setParameter("firstname_eq", criteria.getFirstname_eq());
        }

        if (criteria.getFirstname_like() != null) {
            query.setParameter("firstname_like", criteria.getFirstname_like());
        }

        if (criteria.getLastname_eq() != null) {
            query.setParameter("lastname_eq", criteria.getLastname_eq());
        }

        if (criteria.getLastname_like() != null) {
            query.setParameter("lastname_like", criteria.getLastname_like());
        }

        if (criteria.getMobile_eq() != null) {
            query.setParameter("mobile_eq", criteria.getMobile_eq());
        }

        if (criteria.getMobile_like() != null) {
            query.setParameter("mobile_like", criteria.getMobile_like());
        }

        if (criteria.getAddress_eq() != null) {
            query.setParameter("address_eq", criteria.getAddress_eq());
        }

        if (criteria.getAddress_like() != null) {
            query.setParameter("address_like", criteria.getAddress_like());
        }

        if (criteria.getYearStart_eq() != null) {
            query.setParameter("yearStart_eq", criteria.getYearStart_eq());
        }

        if (criteria.getYearStart_ge() != null) {
            query.setParameter("yearStart_ge", criteria.getYearStart_ge());
        }

        if (criteria.getYearStart_gt() != null) {
            query.setParameter("yearStart_gt", criteria.getYearStart_gt());
        }

        if (criteria.getYearStart_le() != null) {
            query.setParameter("yearStart_le", criteria.getYearStart_le());
        }

        if (criteria.getYearStart_lt() != null) {
            query.setParameter("yearStart_lt", criteria.getYearStart_lt());
        }

        if (criteria.getSalary_eq() != null) {
            query.setParameter("salary_eq", criteria.getSalary_eq());
        }

        if (criteria.getSalary_ge() != null) {
            query.setParameter("salary_ge", criteria.getSalary_ge());
        }

        if (criteria.getSalary_gt() != null) {
            query.setParameter("salary_gt", criteria.getSalary_gt());
        }

        if (criteria.getSalary_le() != null) {
            query.setParameter("salary_le", criteria.getSalary_le());
        }

        if (criteria.getSalary_lt() != null) {
            query.setParameter("salary_lt", criteria.getSalary_lt());
        }

        if (criteria.getIsAdmin_eq() != null) {
            query.setParameter("isAdmin_eq", criteria.getIsAdmin_eq());
        }

        if (criteria.getLastLogin_eq() != null) {
            query.setParameter("lastLogin_eq", criteria.getLastLogin_eq());
        }

        if (criteria.getLastLogin_ge() != null) {
            query.setParameter("lastLogin_ge", criteria.getLastLogin_ge());
        }

        if (criteria.getLastLogin_gt() != null) {
            query.setParameter("lastLogin_gt", criteria.getLastLogin_gt());
        }

        if (criteria.getLastLogin_le() != null) {
            query.setParameter("lastLogin_le", criteria.getLastLogin_le());
        }

        if (criteria.getLastLogin_lt() != null) {
            query.setParameter("lastLogin_lt", criteria.getLastLogin_lt());
        }

        if (criteria.getEmployeeLevel_eq() != null) {
            query.setParameter("employeeLevel_eq", criteria.getEmployeeLevel_eq());
        }

        if (criteria.getId_eq() != null) {
            query.setParameter("id_eq", criteria.getId_eq());
        }

        if (criteria.getId_ge() != null) {
            query.setParameter("id_ge", criteria.getId_ge());
        }

        if (criteria.getId_gt() != null) {
            query.setParameter("id_gt", criteria.getId_gt());
        }

        if (criteria.getId_le() != null) {
            query.setParameter("id_le", criteria.getId_le());
        }

        if (criteria.getId_lt() != null) {
            query.setParameter("id_lt", criteria.getId_lt());
        }

    }

}
