package com.idoc.protocol.server.udp;

import org.apache.mina.core.session.IoSession;

import com.idoc.constant.LogConstant;
import com.idoc.protocol.message.RequestMessage;
import com.idoc.protocol.message.ResponseMessage;
import com.idoc.protocol.plugin.PluginFactory;
import com.idoc.protocol.server.RequestMessageDispatcher;

public class ServerHandlerThread implements Runnable {

	private IoSession session;
	private Object message;
	private RequestMessageDispatcher udpRequestMessageDispatcher;

	public ServerHandlerThread(IoSession session, Object message,
			RequestMessageDispatcher udpRequestMessageDispatcher) {
		this.message = message;
		this.session = session;
		this.udpRequestMessageDispatcher = udpRequestMessageDispatcher;
	}

	@Override
	public void run() {
		if (message instanceof RequestMessage) {
			RequestMessage request = (RequestMessage) message;
			LogConstant.runLog.info("[" + session.getRemoteAddress() + "] UDP请求的内容为：" + request.getRequest());
			String responseMes = udpRequestMessageDispatcher.dispatch(session, request);
			LogConstant.runLog.info("[" + session.getRemoteAddress() + "] UDP请求处理后的响应的内容为：" + responseMes);
			responseMes = PluginFactory.renderFilterPlugin(request.getRequest(), responseMes);
			ResponseMessage response = new ResponseMessage();
			response.setResponse(responseMes);
			LogConstant.runLog.info("[" + session.getRemoteAddress() + "] UDP过滤器处理后的响应的内容为：" + responseMes);
			session.write(response);
		} else {
			LogConstant.debugLog.error("[" + session.getRemoteAddress() + "] UDP请求无效，请求内容为：" + message.toString());
		}
	}
}