package global;

import local.Installer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public enum  Args{
    exit, install, uninstall, ee, version, user, init, load, invoke, bench, stop, ssh, info, membersOnly, members, clients,
    msg, sleep, homeUser, homeIp, homeCwd, homeInfile, clean, stopped, membersLogs, ID,
}

