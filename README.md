# Pepello Backend

Bu proje, Yazılım Tasarım dersi için geliştirilmiştir. 
Proje Trello tarzı bir takım görev takip sistemidir.

## Proje Ekibi

- [Emre Tiryaki](https://github.com/emre-tiryaki)
- [Mert Pepele](https://github.com/Mellomert)
- [Ali Emeksiz](https://github.com/AliEmeksiz)

## Kullanılan Tasarım Desenleri

### 1- Observer Pattern
- **Açıklama:** Bir nesnenin durumu değiştiğinde, bağımlı nesnelerin otomatik olarak bilgilendirilmesini sağlar. 
- **Kullanım Yeri:** Proje oluşturulduğunda (`ProjectServiceImpl`), observer'lar (`ProjectObserverImpl`) bilgilendirilerek projeye otomatik state ve task'lar eklenir.  `ObserverManager` ile observer yönetimi yapılır.

### 2- Prototype Pattern
- **Açıklama:** Var olan nesnelerin kopyalarını oluşturmak için kullanılır.
- **Kullanım Yeri:** `Task` ve `State` entity'lerinde `Prototype<T>` interface'i implement edilerek `cloneEntity()` metodu ile nesnelerin kopyaları oluşturulabilir.   Bu sayede template'lerden yeni task ve state'ler türetilebilir. 

### 3- Template Method Pattern
- **Açıklama:** Bir algoritmanın iskeletini tanımlar, alt sınıfların bazı adımları override etmesine izin verir.
- **Kullanım Yeri:** `BaseCrudService` abstract sınıfı CRUD operasyonlarının genel akışını tanımlar.  `buildEntity()` ve `updateEntity()` abstract metodları, alt sınıflar (`ProjectServiceImpl`, `TaskServiceImpl` vb.) tarafından implemente edilir.

### 4- Facade Pattern
- **Açıklama:** Karmaşık alt sistemlere basit bir arayüz sağlar. 
- **Kullanım Yeri:** `DashboardFacade`, proje dashboard'unu oluşturmak için `ProjectService`, `ProjectStateRelationService` ve `TaskService` gibi birden fazla servisi bir araya getirir ve basitleştirilmiş bir arayüz sunar.

### 5- Builder Pattern
- **Açıklama:** Karmaşık nesnelerin adım adım oluşturulmasını sağlar.
- **Kullanım Yeri:** Entity sınıflarında (`Project`, `Task`, `State`, `Team` vb.) Lombok'un `@Builder` annotation'ı ile builder pattern kullanılır.  Bu sayede nesneler okunabilir ve esnek bir şekilde oluşturulabilir.

### 6- Factory Method
- **Açıklama:** Bir metodun işinin alt sınıflarda tanımlanmasını ve bu sayede implemente edilen sınıfa özel bir metot yazılmış olması
- **Kullanım Yeri:** `BaseCrudService` gibi soyut yaratıcılar `TaskServiceImpl` gibi sınıflar tarafından miras alınıp içi boş metotları doldurup kullanırlar.
