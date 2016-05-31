<div id="table-of-contents">
<h2>Table of Contents</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#orgheadline1">1. Prerequisites</a></li>
<li><a href="#orgheadline2">2. Deployment</a></li>
<li><a href="#orgheadline3">3. To do <code>[0/1]</code></a></li>
</ul>
</div>
</div>

Analyse de fichiers journaux (log) dans un environnement distribuée à l'aide du système multi-agents JADE.

# Prerequisites<a id="orgheadline1"></a>

-   Install `logwatch` in your remote server :
    -   `sudo apt-get install logwatch`
-   `mkdir /var/log/logwatch`

# Deployment<a id="orgheadline2"></a>

-   `cd script`
-   For run an Administrator in local :
    -   `./admin.sh`
-   For run a User agent in remote computer :
    -   `./user.sh <ip>` : `<ip>` is the IP of the Administrator agent computer.

# To do <code>[0/1]</code><a id="orgheadline3"></a>

-   [ ] Deployment in external network
