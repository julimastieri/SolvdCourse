package com.solvd.OnlineShop.dao.mysql;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.solvd.OnlineShop.dao.IOrderDAO;
import com.solvd.OnlineShop.models.Order;

public class OrderDAO extends MySQLAbstractDAO implements IOrderDAO {

	private static final Logger logger = Logger.getLogger(ProductDAO.class);
	private final static String GET_ORDER = "SELECT * FROM Orders o where o.id=?";

	public Order getOrderById(long id) {

		Order o = null;

		try {
			con = pool.getAConnection();
			pr = con.prepareStatement(GET_ORDER);
			pr.setLong(1, id);
			rs = pr.executeQuery();

			if (rs.next()) {
				o = new Order(rs.getInt("id"), rs.getFloat("total"), rs.getFloat("discount"));
			}

		} catch (InterruptedException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error(e);
			}
			try {
				pr.close();
			} catch (SQLException e) {
				logger.error(e);
			}
			try {
				pool.releaseConnection(con);
			} catch (InterruptedException e) {
				logger.error(e);
			}
		}
		return o;
	}

	@Override
	public Order save(Order e, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeById(long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
