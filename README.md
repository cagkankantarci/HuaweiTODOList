# TODOList

All project files (PROJECT / README / PHP / APK): http://cagkankantarci.com/HUAWEI_PROJECT_TO_DO_LIST.rar

PHP: http://cagkankantarci.com/PHP.rar

APK: http://cagkankantarci.com/APK.rar

# OKU BENİ 

**_Sisteme Giriş:_**  

> Email: admin@admin.com , Şifre: admin

**Aktivasyon Hakkında:** 

> Sisteme kendiniz de üye olup girebilirsiniz. Üye olurken aktivasyon süreci bulunmaktadır. Aktivasyon için gelen mail 2 ila 5 dakika arasında, kullandığım sunucu kaynaklı olup mail yavaş gelmektedir. 

**Kütüphaneler:** 

> -Retrofit 

> -Swipe Layout

> -Times Square

> -RecyclerView

> -Circle ImageView

**Tablolar:**

> huawei_users

> ![huawei_users](https://user-images.githubusercontent.com/32047075/62421695-6ebdad80-b6ae-11e9-8dc8-0343fc31f28a.png)

> huawei_all_list

> ![huawei_all_list](https://user-images.githubusercontent.com/32047075/62421689-58175680-b6ae-11e9-8387-9a02ab557b91.png)

> huawei_category

> ![huawei_category](https://user-images.githubusercontent.com/32047075/62421692-61082800-b6ae-11e9-8912-1216fe56f341.png)

> huawei_otherscategory

> ![huawei_otherscategory](https://user-images.githubusercontent.com/32047075/62421693-65ccdc00-b6ae-11e9-8cb7-2ff7626753d6.png)

> 

**Kurulum ve Çalıştırma:**

> Projeyi direk olarak Android Studio’da çalıştırmanız yeterli olacaktır. Onun haricinde dokümanlarımın arasında projenin “apk” sı da eklenmiştir. Direk olarak herhangi bir Android telefona atıp, kurulumu gerçekleştirebilirsiniz. Herhangi bir şey yüklemenize gerek yoktur. 
> Proje hakkında yukarıda bahsedilmiş olan tablolar kullanılmıştır ve bunlar arasında bağlantılar sağlanmıştır. Veritabanı olarak lokal bir yere kaydetmektense, her kullanıcı isteği zaman interneti aktif olan bir telefondan girebilmesi için sanal bir veritabanı yaratıldı. Bunu da Retrofit kütüphanesi kullanılarak yapılmıştır. 
> Tablolarımı bağlarken ki yazılmış olan SQL komutlarının dosyalarını(PHP’ler),doküman dosyalarımın arasında bulunmaktadır. 

**Not:**


> - Kullanıcı Liste’yi oluştururken özellikle başlangıçta oluşturmasına gerek kalmadan, listesini girerken oluşturma imkanı sağlanmıştır. Uygulamaya 4 adet default olarak kategori eklendi. Kullanıcı kendi eklediklerini de “Other Lists” kısmından görebilecek. 
> - Filtrelemede isme göre filtrelemeyi özellikle herhangi bir yere tıklayarak değil, uygulamaya kullanıcı girişi yaptıktan sonra sağ üsteki arama butonundan eklediğiniz listenin ismine göre arama yapabiliyorsunuz. 
> - Kullanıcı eklediği listeyi “Settings” bölümünden silebiliyor. Eğer daha önceden eklemediyse Checkbox’lar “Invisible” durumundadır. Göremeyecektir. 
> - Kullanıcı eklediği liste için maddeleri istediği kategoriye girip, istediği kategoriden silmek için ekrandaki itemları sağa veya sola kaydırarak silme işlemi gerçekleştirebilir. Kullanıcı eğer ki Ana Sayfa’dan silerse tüm kategorilerden eklediği itemlar silinecektir.
> - Ekleme,filtreleme ve sıralama’yı ekranın sağ alt köşesinden “Floating Action Button” yardımıyla istediği işlemi yapmak için seçim yapabilir. 
> - Kullanıcı’nın bilgileri sanal bir veritabanında kayıt ediliyor fakat kendi local değişkenine listesini kaydetmek isterse. “Settings” bölümünden exportlama imkanı verildi. 

```
Açıklamamı istediğiniz herhangi bir bölüm varsa, mail atmanız yeterlidir. 
Teşekkürler. İyi Çalışmalar.
Çağkan Kantarcı / cagkankantarci@gmail.com
```

# READ ME

**_Login Information:_**  

> Email: admin@admin.com , Password: admin

**About Activation:** 

> Sisteme kendiniz de üye olup girebilirsiniz. Üye olurken aktivasyon süreci bulunmaktadır. Aktivasyon için gelen mail 2 ila 5 dakika arasında, kullandığım sunucu kaynaklı olup mail yavaş gelmektedir. 

**Libraries:** 

> -Retrofit 

> -Swipe Layout

> -Times Square

> -RecyclerView

> -Circle ImageView

**Tables:**

> huawei_users

> ![huawei_users](https://user-images.githubusercontent.com/32047075/62421695-6ebdad80-b6ae-11e9-8dc8-0343fc31f28a.png)

> huawei_all_list

> ![huawei_all_list](https://user-images.githubusercontent.com/32047075/62421689-58175680-b6ae-11e9-8387-9a02ab557b91.png)

> huawei_category

> ![huawei_category](https://user-images.githubusercontent.com/32047075/62421692-61082800-b6ae-11e9-8912-1216fe56f341.png)

> huawei_otherscategory

> ![huawei_otherscategory](https://user-images.githubusercontent.com/32047075/62421693-65ccdc00-b6ae-11e9-8cb7-2ff7626753d6.png)
> 

**Installation and Operation:**

> Just run the project directly in Android Studio. In addition, the project's APK has been added, you can see in my document files. Directly to any Android phone, you can perform installation. You do not need to install anything. Application is stable.


**Note:**
> 
> - While adding the item that the user wants to add, it is also possible to add a list. 4 default categories were added to the application. The user will be able to see his / her own entries in the “Other Lists ”section.
> - In filtering, filtering by name is not particularly clicking anywhere. In the application you can search by the name of the list you added from the search button on the top right.
> - The user can delete his/her list from the “Settings” section. If there are no previously added lists, the checkboxes are Invisible. He/she will not see.
> - If the user wants to delete the items that he/she added, he/she can enter the category and delete item. If the user deletes from the Home Page, all items will be deleted from all categories.
> - You can choose to add, filter, and sort from the bottom right corner of the screen using the Floating Action Button.
> - The user's information is saved in a virtual database, but he wants to save the list in his local variable. “Settings” section was given the possibility to export.


```
If there is any section you would like me to explain, just send an e-mail. 
Thanks. Good work.
Çağkan Kantarcı / cagkankantarci@gmail.com

```
