﻿using Lacuna.RestPki.Client;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PkiSuiteAspNetMvcSample.Models.RestPki {
	public class AuthenticationSuccessModel {
		public PKCertificate UserCert { get; set; }
	}
}