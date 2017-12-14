/**
 * 
 */
package com.nisum.carpool.data.dao.api;

import java.util.List;

import com.nisum.carpool.data.domain.Carpooldetails;

/**
 * @author vkallada
 * CreatedDate 14th Dec.
 *
 */
public interface IDriverPoolHistoryDAO {

	List<Carpooldetails> getDriverPoolHistory(Carpooldetails carpooldetails);
}
