Installation process:
1. Decide on swift device: either loop or real. Once decided - set vars.yml-sample accordingly and copy to vars.yml
2. Prepare the loop device if this is the choice
3. Create the cluster_config file that describes the cluster based on a template.
4. pull the Swift ansible installation script
5. Copy cluster_config to the proper location of the Swift ansible installation script
6. Invoke swift installation: cd to repo/provisioniing, ansible-playbook tralala
