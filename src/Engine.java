public interface Engine {

    // on ne peut pas instancier d'interface
    // une classe peut implémenter Engine à condition de @Override la méthode update();
    // une classe peut implémenter plusieurs interfaces

    public void update();
}
