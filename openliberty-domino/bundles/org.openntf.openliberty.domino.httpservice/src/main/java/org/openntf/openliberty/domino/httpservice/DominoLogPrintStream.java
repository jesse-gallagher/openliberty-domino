/**
 * Copyright © 2018-2020 Jesse Gallagher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openntf.openliberty.domino.httpservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openntf.openliberty.domino.ext.LoggerPrintStream;
import org.openntf.openliberty.domino.util.commons.ibm.StringUtil;

import com.ibm.commons.util.io.NullOutputStream;
import com.ibm.domino.napi.c.Os;

public class DominoLogPrintStream extends LoggerPrintStream {
	
	public DominoLogPrintStream() {
		super(new NullOutputStream());
	}
	
	// TODO non-US support
	private static final ThreadLocal<DateFormat> DT_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("MM/dd/yyy hh:mm:ss a")); //$NON-NLS-1$
	
	@Override
	protected void _line(String message) {
		String prefix = getPrefix();
		String time = DT_FORMAT.get().format(new Date());
		if(StringUtil.isEmpty(prefix)) {
			Os.OSConsoleWrite(time + "  " + StringUtil.toString(message).replace("%", "%%") + '\n'); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else {
			Os.OSConsoleWrite(time + "  " + prefix + ": " + StringUtil.toString(message).replace("%", "%%") + '\n'); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
	}
}