/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.service;

import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.ReplyBean;
import net.daw.bean.UsuarioBean;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.constant.ConnectionConstants;
import net.daw.dao.UsuarioDao;
import net.daw.factory.ConnectionFactory;
import net.daw.helper.EncodingHelper;

/**
 *
 * @author jesus
 * @author alejandro
 */
public class UsuarioService {
    HttpServletRequest oRequest;
	String ob = null;

	public UsuarioService(HttpServletRequest oRequest) {
		super();
		this.oRequest = oRequest;
		ob = oRequest.getParameter("ob");
	}

	public ReplyBean get() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			Integer id = Integer.parseInt(oRequest.getParameter("id"));
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			UsuarioDao oUsuarioDao = new UsuarioDao(oConnection, ob);
			UsuarioBean oUsuarioBean = oUsuarioDao.get(id);
			Gson oGson = new Gson();
			oReplyBean = new ReplyBean(200, oGson.toJson(oUsuarioBean));
		} catch (Exception ex) {		
			 throw new Exception("ERROR: Service level: get method: " + ob + " object", ex);			
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}

	public ReplyBean remove() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			Integer id = Integer.parseInt(oRequest.getParameter("id"));
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			UsuarioDao oUsuarioDao = new UsuarioDao(oConnection, ob);
			int iRes = oUsuarioDao.remove(id);
			oReplyBean = new ReplyBean(200, Integer.toString(iRes));
		} catch (Exception ex) {
			throw new Exception("ERROR: Service level: remove method: " + ob + " object", ex);		} finally {
			oConnectionPool.disposeConnection();
		}
		return oReplyBean;

	}

	public ReplyBean getcount() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			UsuarioDao oUsuarioDao = new UsuarioDao(oConnection, ob);
			int registros = oUsuarioDao.getcount();
			Gson oGson = new Gson();
			oReplyBean = new ReplyBean(200, oGson.toJson(registros));
		} catch (Exception ex) {
			throw new Exception("ERROR: Service level: getcount method: " + ob + " object", ex);
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}

	public ReplyBean create() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			String strJsonFromClient = oRequest.getParameter("json");
			Gson oGson = new Gson();
			UsuarioBean oUsuarioBean = new UsuarioBean();
			oUsuarioBean = oGson.fromJson(strJsonFromClient, UsuarioBean.class);
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			UsuarioDao oUsuarioDao = new UsuarioDao(oConnection, ob);
			oUsuarioBean = oUsuarioDao.create(oUsuarioBean);
			oReplyBean = new ReplyBean(200, oGson.toJson(oUsuarioBean));
		} catch (Exception ex) {
			throw new Exception("ERROR: Service level: create method: " + ob + " object", ex);
		} finally {
			oConnectionPool.disposeConnection();
		}
		return oReplyBean;
	}

	public ReplyBean update() throws Exception {
		int iRes = 0;
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			String strJsonFromClient = oRequest.getParameter("json");
			Gson oGson = new Gson();
			UsuarioBean oUsuarioBean = new UsuarioBean();
			oUsuarioBean = oGson.fromJson(strJsonFromClient, UsuarioBean.class);
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			UsuarioDao oUsuarioDao = new UsuarioDao(oConnection, ob);
			iRes = oUsuarioDao.update(oUsuarioBean);
                        oReplyBean = new ReplyBean(200, Integer.toString(iRes));
		} catch (Exception ex) {
			throw new Exception("ERROR: Service level: update method: " + ob + " object", ex);
		} finally {
			oConnectionPool.disposeConnection();
		}
		return oReplyBean;
	}

	public ReplyBean getpage() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			Integer iRpp = Integer.parseInt(oRequest.getParameter("rpp"));
			Integer iPage = Integer.parseInt(oRequest.getParameter("page"));
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			UsuarioDao oUsuarioDao = new UsuarioDao(oConnection, ob);
			ArrayList<UsuarioBean> alUsuarioBean = oUsuarioDao.getpage(iRpp, iPage);
			Gson oGson = new Gson();
			oReplyBean = new ReplyBean(200, oGson.toJson(alUsuarioBean));
		} catch (Exception ex) {
			throw new Exception("ERROR: Service level: get page: " + ob + " object", ex);
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}
        
        	public ReplyBean getpageorder() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			Integer iRpp = Integer.parseInt(oRequest.getParameter("rpp"));
			Integer iPage = Integer.parseInt(oRequest.getParameter("page"));
                        String sOrderBy = oRequest.getParameter("orderby");
                        String[] aOrderBy = sOrderBy.split(",");
                        String sField = aOrderBy[0];
                        String sOrder = aOrderBy[1];
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			UsuarioDao oUsuarioDao = new UsuarioDao(oConnection, ob);
			ArrayList<UsuarioBean> alUsuarioBean = oUsuarioDao.getpageorder(iRpp, iPage, sField, sOrder);
			Gson oGson = new Gson();
			oReplyBean = new ReplyBean(200, oGson.toJson(alUsuarioBean));
		} catch (Exception ex) {
			throw new Exception("ERROR: Service level: get page: " + ob + " object", ex);
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}
	
    public ReplyBean filltable() throws Exception {
        ReplyBean oReplyBean;
        ConnectionInterface oConnectionPool = null;
        Connection oConnection;
        ArrayList<UsuarioBean> usuarios = new ArrayList<>();
        FillService oFillService = new FillService();
        try {
            Integer number = Integer.parseInt(oRequest.getParameter("number"));
            oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
            oConnection = oConnectionPool.newConnection();
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection, ob);
            usuarios = oFillService.FillUsuario(number);
            for (UsuarioBean usuario : usuarios) {
            	oUsuarioDao.create(usuario);
            }
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson("Usuarios creados: " + number));
        } catch (Exception ex) {
            oReplyBean = new ReplyBean(500,
                    "ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
        }
        return oReplyBean;
    }
	
	
}
