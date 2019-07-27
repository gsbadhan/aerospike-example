# aerospike examples

# aerospike setup on vagrant :
1. $ mkdir aerospike-vm
2. $ cd aerospike-vm
3. $ vagrant init aerospike/aerospike-ce
4. $ vagrant up
5. $ vagrant ssh -c "sudo service aerospike status"
6. $ vagrant ssh -c "sudo service amc status"

Setup reference:- https://www.aerospike.com/docs/operations/install/vagrant/mac/using-vagrant.html

# how to use aerospike console :
1. $ vagrant ssh
2. $ aql

