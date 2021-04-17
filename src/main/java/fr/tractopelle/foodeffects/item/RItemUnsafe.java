package fr.tractopelle.foodeffects.item;

public class RItemUnsafe {

    private ItemBuilder RItemBuilder;

    public RItemUnsafe(ItemBuilder RItemBuilder) {
        this.RItemBuilder = RItemBuilder;
    }

    public String getString(String tag){
        return new fr.tractopelle.foodeffects.item.RNBItem(this.RItemBuilder.toItemStack()).getString(tag);
    }

    public RItemUnsafe setString(String tag, String value){
        this.RItemBuilder = new ItemBuilder(new fr.tractopelle.foodeffects.item.RNBItem(this.RItemBuilder.toItemStack()).setString(tag, value).build());
        return this;
    }

    public RItemUnsafe setByte(String tag, byte b){
        this.RItemBuilder = new ItemBuilder(new fr.tractopelle.foodeffects.item.RNBItem(this.RItemBuilder.toItemStack()).setByte(tag, b).build());
        return this;
    }

    public byte getByte(String tag){
        return new RNBItem(this.RItemBuilder.toItemStack()).getByte(tag);
    }

    public RItemUnsafe setShort(String tag, short b){
        this.RItemBuilder = new ItemBuilder(new fr.tractopelle.foodeffects.item.RNBItem(this.RItemBuilder.toItemStack()).setShort(tag, b).build());
        return this;
    }

    public short getShort(String tag){
        return new RNBItem(this.RItemBuilder.toItemStack()).getShort(tag);
    }

    public RItemUnsafe setInt(String tag, int b){
        this.RItemBuilder = new ItemBuilder(new RNBItem(this.RItemBuilder.toItemStack()).setInt(tag, b).build());
        return this;
    }

    public int getInt(String tag){
        return new RNBItem(this.RItemBuilder.toItemStack()).getInt(tag);
    }

    public RItemUnsafe setByteArray(String tag, byte[] b){
        this.RItemBuilder = new ItemBuilder(new RNBItem(this.RItemBuilder.toItemStack()).setByteArray(tag, b).build());
        return this;
    }

    public byte[] getByteArray(String tag){
        return new RNBItem(this.RItemBuilder.toItemStack()).getByteArray(tag);
    }

    public RItemUnsafe setIntArray(String tag, int[] b){
        this.RItemBuilder = new ItemBuilder(new RNBItem(this.RItemBuilder.toItemStack()).setIntArray(tag, b).build());
        return this;
    }

    public int[] getIntArray(String tag){
        return new RNBItem(this.RItemBuilder.toItemStack()).getIntArray(tag);
    }

    public boolean containsTag(String s){
        return new RNBItem(this.RItemBuilder.toItemStack()).containsTag(s);
    }

    public RItemUnsafe remove(String tag){
        this.RItemBuilder = new ItemBuilder(new RNBItem(this.RItemBuilder.toItemStack()).removeTag(tag).build());
        return this;
    }

    public ItemBuilder toItemBuilder(){
        return this.RItemBuilder;
    }
}