sudo docker run --init -d --name test-se -v /home/ramee/DockerSelenium:/usr/games gradle:7.6.0-jdk19  /bin/tail -f /dev/null

export PATH=$PATH:/usr/games/DockerSelenium/driver


apt-get update
apt-get install libglib2.0-0
apt-get install libnss3
apt-get install libxcb1 


apt-get install gpg

curl https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > microsoft.gpg
install -o root -g root -m 644 microsoft.gpg /etc/apt/trusted.gpg.d/
sh -c 'echo "deb [arch=amd64] https://packages.microsoft.com/repos/edge stable main" > /etc/apt/sources.list.d/microsoft-edge-dev.list'
rm microsoft.gpg

apt update
apt install microsoft-edge-stable
sudo apt remove microsoft-edge-stable 



•¶Žš‰»‚¯
apt-get install -y fonts-takao-gothic fonts-noto-cjk
apt-get install fonts-noto-cjk

W: Target Packages (main/binary-amd64/Packages) is configured multiple times in /etc/apt/sources.list.d/microsoft-edge-dev.list:1 and /etc/apt/sources.list.d/microsoft-edge.list:3
W: Target Packages (main/binary-all/Packages) is configured multiple times in /etc/apt/sources.list.d/microsoft-edge-dev.list:1 and /etc/apt/sources.list.d/microsoft-edge.list:3


