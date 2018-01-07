import sys
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
from firebase_admin import auth


'''
    @show_all_users func to print the all data base "Users"
'''
def show_all_users():
    ref = db.reference('Users')
    print("In progress...\nThe Users:")
    dict = ref.get()
    if(dict != None):
        list=[]
        for uid in dict.keys():
            list.append(uid)
        index = 0
        for user in dict.values():
            num=(index+1)
            print "User number: " + str(num) + "\nUID: " + list[index]
            for key, val in user.items():
                print key +": "+ val
            print
            index += 1
    else:
        print "No Users in the database !!!"


'''
    @delete_user_by_input func to delete User by UID
'''
def delete_user_by_input():
    show_all_users()
    print "Choose an UID from the list to delete"
    ref = db.reference('Users')
    dict = ref.get()
    if dict != None :
        while True:
            choose = (raw_input("Please enter your choice: "))
            for uid in dict.keys():
                if uid == choose:
                    auth.delete_user(choose)
                    print "User has been deleted"
                    ref.child(uid).delete()
                    return
            print "Problem with the uid please choose again"
    else:
        print "No Users in the database !!!"

'''
@add_new_user func to add user to auth system and to real time database
'''
def add_new_user():
    userName = (raw_input("Please enter user name: "))
    firstName = (raw_input("Please enter first name: "))
    lastName = (raw_input("Please enter last name: "))
    password1 = (raw_input("Please enter password(at least 6 digits): "))
    city = (raw_input("Please enter city: "))
    street = (raw_input("Please enter street: "))
    email1 = (raw_input("Please enter valid email: "))
    userAuth = auth.create_user(email=email1, password=password1)
    ref = db.reference('Users').child(userAuth.uid)
    ref.set({
        'userName': userName,
        'firstName': firstName,
        'lastName': lastName,
        'password': password1,
        'city': city,
        'street': street,
        'email': email1,

    })
    print "User has been added!!!"


    return


'''
@func my Menu
'''
def menu():
    print("Menu!!!\nPrees 1 to Show all users or 2 Delete user by Input or 3 Add new user\n"
          "1: Show all users\n2: Delete user by Input\n3: Add new user\n4: Exit")
    while True:
        try:
            choose = int(raw_input("Please enter your choice: "))
            if choose != 1 and choose != 2 and choose !=3 and choose !=4:
                raise ValueError
        except ValueError:
            print("Sorry, please enter again num between 1 to 4.")
            continue
        else:
            break
    if choose == 1:  # user choosed to Show all Users
        show_all_users()
    elif choose == 2:
        delete_user_by_input()
    elif choose == 3:
        add_new_user()
    else:
        print("Thank you for using this amazing app by Eliran, Noy and Shahar")
        sys.exit()



cred = credentials.Certificate("canvas-fulcrum-187913-firebase-adminsdk-fc5dj-05ac946e8d.json")
firebase_admin.initialize_app(cred, {'databaseURL': 'https://canvas-fulcrum-187913.firebaseio.com'})
while True:
    menu()