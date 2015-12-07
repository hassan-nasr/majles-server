package JerseyTest;

import com.idehgostar.makhsan.core.auth.TokenData;
import com.idehgostar.makhsan.core.auth.TokenManager;
import com.idehgostar.makhsan.core.services.ApplicationService;
import com.idehgostar.makhsan.core.services.ApplicationServiceManager;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.container.filter.LoggingFilter;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerException;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import dbfill.MajlesTestDataFiller;
import ir.hassannasr.majles.domain.init.MajlesInitDataFiller;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Test class which will wire itelf into your the Spring context which
 * is configured on the WebAppDecriptor built for your tests.
 * Ensure you configure annotation-aware support into your contexts,
 * and annotate any auto-wire properties on your test class
 * @author George McIntosh
 *
 */


public abstract class AbstractSpringAwareJerseyTest extends JerseyTest {

	@Autowired
	ApplicationServiceManager applicationServiceManager;
	@Autowired
	TokenManager tokenManager;

	public AbstractSpringAwareJerseyTest() {
		super();
	}


	public AbstractSpringAwareJerseyTest(WebAppDescriptor wad) {
		super(wad);
	}

	@BeforeClass
	public static void setUp3() throws Exception {

		MajlesInitDataFiller.main(new String[]{"true", "drop"});
		MajlesTestDataFiller.main(new String[]{"true"});

	}

	protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
		return new SpringAwareGrizzlyTestContainerFactory(this);
	}

	@Override
	protected int getPort(int defaultPort) {

		ServerSocket server = null;
		int port = -1;
		try {
			server = new ServerSocket(defaultPort);
			port = server.getLocalPort();
		} catch (IOException e) {
			// ignore
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
		if ((port != -1) || (defaultPort == 0)) {
			return port;
		}
		return getPort(0);
	}

	@Override
	public AppDescriptor configure() {

		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(JacksonJsonProvider.class);
		WebAppDescriptor wa = new WebAppDescriptor.Builder()
				.contextPath("/")
				.contextParam("contextConfigLocation",
						"classpath:applicationContext-majles-resource-test.xml " +
								"classpath:applicationContext-majles-persistence.xml " +
								"classpath:applicationContext-majles-security.xml " +
								"classpath:applicationContext-majles-service.xml " +
								"classpath:applicationContext-majles.xml "

				)
				.contextListenerClass(ContextLoaderListener.class)
				.servletClass(SpringServlet.class)
				.initParam(ResourceConfig.FEATURE_TRACE, "true")
				.initParam(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS, LoggingFilter.class.getCanonicalName())
				.initParam(ResourceConfig.PROPERTY_CONTAINER_NOTIFIER, LoggingFilter.class.getCanonicalName())
				.initParam(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, LoggingFilter.class.getCanonicalName())
				.initParam(JSONConfiguration.FEATURE_POJO_MAPPING, "true")
				.addFilter(org.springframework.web.filter.DelegatingFilterProxy.class, "authenticationInfoExtractor")
				.build();
		return wa;
	}

	@Before
	public void setUp2() throws Exception {

		MajlesInitDataFiller.main(new String[]{"true", "drop"});
		MajlesTestDataFiller.main(new String[]{"true"});

	}

	protected String generateAccessToken(String userId, Set<String> permissions) {
		try {
			ApplicationService defaultService = applicationServiceManager.loadDefaultService();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy");

			Date eDate = simpleDateFormat.parse("11/11/2020");

			TokenData tokenData = new TokenData(userId, null, new Date(), eDate, permissions);
			return tokenManager.createTokenString(tokenData, defaultService);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
