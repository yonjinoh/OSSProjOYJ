from django.contrib import admin

# Register your models here.
from .models import AppUser, Chat, ChatRoom, Profile, UserPref, Match, Report, Block


admin.site.register(AppUser)
admin.site.register(Chat)
admin.site.register(ChatRoom)
admin.site.register(Profile)
admin.site.register(UserPref)
admin.site.register(Match)
admin.site.register(Report)
admin.site.register(Block)
