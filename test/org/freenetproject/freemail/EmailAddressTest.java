/*
 * EmailAddressTest.java
 * This file is part of Freemail, copyright (C) 2012 Martin Nyhus
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package org.freenetproject.freemail;

import static org.junit.Assert.*;

import org.junit.Test;

import org.freenetproject.freemail.utils.EmailAddress;

public class EmailAddressTest {
	@Test
	public void simpleAddress() {
		checkAddressPasing("zidel@b5zswai7ybkmvcrfddlz5euw3ifzn5z5m3bzdgpucb26mzqvsflq.freemail",
		                   null, "zidel", "b5zswai7ybkmvcrfddlz5euw3ifzn5z5m3bzdgpucb26mzqvsflq.freemail");
	}

	@Test
	public void simpleAddressWithName() {
		checkAddressPasing("Zidel <zidel@b5zswai7ybkmvcrfddlz5euw3ifzn5z5m3bzdgpucb26mzqvsflq.freemail>",
		                   "Zidel", "zidel", "b5zswai7ybkmvcrfddlz5euw3ifzn5z5m3bzdgpucb26mzqvsflq.freemail");
	}

	@Test
	public void addressWithoutAt() {
		try {
			//Unused since the point is to check that the constructor throws
			@SuppressWarnings("unused")
			EmailAddress emailAddress = new EmailAddress("zidel");

			fail("Should not be able to create email address without @");
		} catch(IllegalArgumentException e) {
			//Expected
		}
	}

	@Test
	public void addressWithUTF8() {
		try {
			//Unused since the point is to check that the constructor throws
			@SuppressWarnings("unused")
			EmailAddress emailAddress = new EmailAddress("æøå@email.com");

			fail("Should not be able to create email address with UTF-8");
		} catch(IllegalArgumentException e) {
			//Expected
		}
	}

	private void checkAddressPasing(String address, String expectedName, String expectedLocal, String expectedDomain) {
		EmailAddress email = new EmailAddress(address);
		assertEquals(expectedName, email.realname);
		assertEquals(expectedLocal, email.user);
		assertEquals(expectedDomain, email.domain);
	}
}
