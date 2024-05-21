from django.contrib import admin

# Register your models here.
from .models import Room, AppUser, Chat, Profile, UserPref, Match, Report, Block

admin.site.register(Room)
admin.site.register(AppUser)
admin.site.register(Chat)
admin.site.register(Profile)
admin.site.register(UserPref)
admin.site.register(Match)
admin.site.register(Report)
admin.site.register(Block)
