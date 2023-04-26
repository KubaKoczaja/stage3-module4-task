package com.mjc.school.main;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

		@Override
		protected Class[] getServletConfigClasses() {
				return new Class[] { SpringConfig.class };
		}

		@Override
		protected String[] getServletMappings() {
				return new String[] { "/" };
		}

		@Override
		protected Class[] getRootConfigClasses() {
				return new Class[] {};
		}
}

