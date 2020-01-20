SHOW CREATE TABLE m_company;
SHOW CREATE TABLE m_station;
SHOW CREATE TABLE m_line;
SHOW CREATE TABLE m_station_join;

-- m_company
CREATE TABLE `m_company` (
  `company_cd` int(11) NOT NULL DEFAULT '0',
  `rr_cd` smallint(6) NOT NULL DEFAULT '0',
  `company_name` varchar(256) NOT NULL DEFAULT '',
  `company_name_k` varchar(256) DEFAULT NULL,
  `company_name_h` varchar(256) DEFAULT NULL,
  `company_name_r` varchar(256) DEFAULT NULL,
  `company_url` varchar(512) DEFAULT NULL,
  `company_type` smallint(6) DEFAULT '0',
  `e_status` smallint(6) DEFAULT '0',
  `e_sort` int(11) DEFAULT '0',
  PRIMARY KEY (`company_cd`),
  KEY `m_company_rr_cd` (`rr_cd`),
  KEY `m_company_company_type` (`company_type`),
  KEY `m_company_e_sort` (`e_sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- m_station
CREATE TABLE `m_station` (
  `station_cd` int(11) NOT NULL DEFAULT '0',
  `station_g_cd` int(11) NOT NULL DEFAULT '0',
  `station_name` varchar(256) NOT NULL DEFAULT '',
  `station_name_k` varchar(256) DEFAULT NULL,
  `station_name_r` varchar(256) DEFAULT NULL,
  `station_name2` varchar(256) DEFAULT NULL,
  `station_number` varchar(256) DEFAULT NULL,
  `station_u` varchar(256) DEFAULT NULL,
  `line_cd` int(11) NOT NULL DEFAULT '0',
  `pref_cd` smallint(6) DEFAULT '0',
  `post` varchar(32) DEFAULT NULL,
  `address` varchar(1024) DEFAULT NULL,
  `lon` double DEFAULT '0',
  `lat` double DEFAULT '0',
  `open_ymd` date DEFAULT NULL,
  `close_ymd` date DEFAULT NULL,
  `e_status` smallint(6) DEFAULT '0',
  `e_sort` int(11) DEFAULT '0',
  PRIMARY KEY (`station_cd`),
  KEY `m_station_station_g_cd` (`station_g_cd`),
  KEY `m_station_line_cd` (`line_cd`),
  KEY `m_station_pref_cd` (`pref_cd`),
  KEY `m_station_e_sort` (`e_sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- m_line
CREATE TABLE `m_line` (
  `line_cd` int(11) NOT NULL DEFAULT '0',
  `company_cd` int(11) NOT NULL DEFAULT '0',
  `line_name` varchar(256) NOT NULL DEFAULT '',
  `line_name_k` varchar(256) DEFAULT NULL,
  `line_name_h` varchar(256) DEFAULT NULL,
  `line_color_c` varchar(8) DEFAULT NULL,
  `line_color_t` varchar(32) DEFAULT NULL,
  `line_type` smallint(6) DEFAULT '0',
  `lon` double DEFAULT '0',
  `lat` double DEFAULT '0',
  `zoom` smallint(6) DEFAULT '0',
  `e_status` smallint(6) DEFAULT '0',
  `e_sort` int(11) DEFAULT '0',
  PRIMARY KEY (`line_cd`),
  KEY `m_line_company_cd` (`company_cd`),
  KEY `m_line_e_sort` (`e_sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- m_station_join
CREATE TABLE `m_station_join` (
  `line_cd` int(11) NOT NULL DEFAULT '0',
  `station_cd1` int(11) NOT NULL DEFAULT '0',
  `station_cd2` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`line_cd`,`station_cd1`,`station_cd2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;