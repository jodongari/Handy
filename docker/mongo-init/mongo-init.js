db = db.getSiblingDB('handy');
db.createUser({
    user: 'handy',
    pwd: 'handy',
    roles: [
        {
            role:'readWrite',
            db:'handy'
        }]
});