package org.openntf.openliberty.domino.jvm;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openntf.openliberty.domino.util.commons.ibm.StringUtil;

/**
 * Implementation of {@link JavaRuntimeProvider} that uses the current JVM
 * location.
 * 
 * @author Jesse Gallagher
 * @since 3.0.0
 */
public class RunningJVMJavaRuntimeProvider implements JavaRuntimeProvider {
	public static final String TYPE_RUNNINGJVM = "RunningJVM";

	@Override
	public boolean canProvide(String version, String type) {
		return TYPE_RUNNINGJVM.equals(type);
	}

	@Override
	public Path getJavaHome(String version, String type) {
		String javaHome = System.getProperty("java.home");
		if(StringUtil.isEmpty(javaHome)) {
			throw new IllegalStateException("Unable to locate Java home from java.home property");
		}
		return Paths.get(javaHome);
	}

}